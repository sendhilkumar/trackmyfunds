package tracker.server;

import com.codahale.metrics.annotation.Timed;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.fw.common.mithra.finder.Operation;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.data.portfolio.loader.CASLoader;
import tracker.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

@Path("/portfolio")
@Produces(MediaType.APPLICATION_JSON)
public class PortfolioServices {
    private static final String PASSWORD = "Finz123SM";
    private static Logger logger = LoggerFactory.getLogger(PortfolioServices.class.getName());

    private static final Function<Integer, SchemeValue> NEW_SCHEME = object -> new SchemeValue(SchemeFinder.findByPrimaryKey(object));

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public List<Transaction> upload(@FormDataParam("file") InputStream inputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData) throws IOException {

        final File tempFile = File.createTempFile("statement", "pdf");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(inputStream, out);
        }
        return new CASLoader().loadStatement(tempFile, PASSWORD);
    }

    @Path("subportfolios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PortfolioList getPortfolios() {
        return PortfolioFinder.findMany(PortfolioFinder.all());
    }

    @Path("{portfolioId}/today")
    @GET
    @Timed
    public PortfolioSnapshot getPortfolioValue(@PathParam("portfolioId") int portfolioId) {
        LatestNAVDate latestNAVDate = LatestNAVDateFinder.findOne(LatestNAVDateFinder.all());

        Timestamp today = latestNAVDate.getDate();
        Timestamp priorDay = Timestamp.valueOf(today.toLocalDateTime().minusDays(1));
        logger.info("today : " + today);
        logger.info("priorDay : " + priorDay);
        PortfolioValue todayValue = calculatePortfolioValue(today, portfolioId);
        PortfolioValue priorDayValue = calculatePortfolioValue(priorDay, portfolioId);
        return new PortfolioSnapshot(todayValue, priorDayValue, new PortfolioValueOneDayDelta(todayValue, priorDayValue));
    }

    @Path("{portfolioId}/{asOfDate}/transactions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionList getTransactions(@PathParam("asOfDate") String asOfDate, @PathParam("portfolioId") int portfolioId) {
        return getTransactions(Timestamp.valueOf(asOfDate + " 00:00:00"), portfolioId);
    }

    private TransactionList getTransactions(Timestamp date, int portfolioId) {
        Operation operation = TransactionFinder.date().lessThanEquals(date);
        if (portfolioId > 0) {
            operation = operation.and(TransactionFinder.scheme().portfolio().id().eq(portfolioId));
        }
        return TransactionFinder.findMany(operation);
    }

    @Path("{portfolioId}/history")
    @GET
    @Timed
    public Map<Timestamp, PortfolioValue> getPortfolioHistory(@PathParam("portfolioId") int portfolioId) {

        Operation operation = portfolioId == 0 ? TransactionFinder.all() : TransactionFinder.scheme().portfolio().id().eq(portfolioId);
        TransactionList transactions = TransactionFinder.findMany(operation);
        transactions.addOrderBy(TransactionFinder.date().ascendingOrderBy());

        Map<Timestamp, PortfolioValue> history = new TreeMap<>();
        if (transactions.size() > 0) {
            Transaction firstTransaction = transactions.get(0);
            LocalDateTime firstTransactionDate = firstTransaction.getDate().toLocalDateTime();
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime asOfDate = firstTransactionDate;
            while (asOfDate.isBefore(now)) {
                Timestamp timestamp = Timestamp.valueOf(asOfDate);
                boolean weekend = asOfDate.getDayOfWeek() == DayOfWeek.SATURDAY || asOfDate.getDayOfWeek() == DayOfWeek.SUNDAY;
                if (!weekend) {
                    PortfolioValue portfolioValue = calculatePortfolioValue(timestamp, portfolioId);
                    history.put(timestamp, portfolioValue);
                }
                asOfDate = asOfDate.plusDays(1);
            }

            return history;
        } else
            return new HashMap<>();
    }

    @Path("{portfolioId}/{asOfDate}/value")
    @GET
    @Timed
    public PortfolioValue getPortfolioValue(@PathParam("asOfDate") String asOfDate, @PathParam("portfolioId") int portfolioId) {
        return calculatePortfolioValue(Timestamp.valueOf(asOfDate + " 00:00:00"), portfolioId);
    }

    private PortfolioValue calculatePortfolioValue(Timestamp asOfDate, int portfolio) {
        TransactionList transactions = getTransactions(asOfDate, portfolio);
        transactions.addOrderBy(TransactionFinder.id().ascendingOrderBy());

        MutableMap<Integer, SchemeValue> bySchemeCode = UnifiedMap.newMap();
        double totalValue = 0.0;
        double totalCost = 0.0;

        for (Transaction transaction : transactions) {
            int schemeCode = transaction.getSchemeCode();
            SchemeValue schemeValue = bySchemeCode.getIfAbsentPutWith(schemeCode, NEW_SCHEME, schemeCode);
            schemeValue.addTransaction(transaction);
        }

        List<SchemeValue> schemeValues = FastList.newList();
        for (Map.Entry<Integer, SchemeValue> entry : bySchemeCode.entrySet()) {
            SchemeValue schemeValue = entry.getValue();
            if (schemeValue.getUnits() > 0.001) { //Filtering 0 unit schemes
                calculateSchemeValue(schemeValue, asOfDate);

                schemeValues.add(schemeValue);
                totalValue += schemeValue.getValue();
                totalCost += schemeValue.getCost();
            }
        }
        schemeValues.sort(Comparator.comparing(o -> o.getScheme().getName()));
        return new PortfolioValue(asOfDate, schemeValues, totalCost, totalValue);
    }

    private void calculateSchemeValue(SchemeValue schemeValue, Timestamp asOfDate) {
        Scheme scheme = schemeValue.getScheme();
        NetAssetValue navRecord = NetAssetValueFinder.findByPrimaryKey(scheme.getCode(), asOfDate);

        int fallBackCounter = 0;
        Timestamp fallbackDate = asOfDate;
        while (navRecord == null && fallBackCounter < 7) {
            fallBackCounter++;
            fallbackDate = Timestamp.valueOf(fallbackDate.toLocalDateTime().minusDays(1));
            navRecord = NetAssetValueFinder.findByPrimaryKey(scheme.getCode(), fallbackDate);
        }

        if (navRecord != null) {
            schemeValue.setLatestNAVDate(asOfDate);
            schemeValue.setNav(navRecord.getNetAssetValue());
            schemeValue.calculateXirr();
        } else {
            throw new RuntimeException("Could not find NAV of scheme:" + scheme.getName() + " for date " + asOfDate.toLocalDateTime() + " or for any of 7 days earlier");
        }
    }
}