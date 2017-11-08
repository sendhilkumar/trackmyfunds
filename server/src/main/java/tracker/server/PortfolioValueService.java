package tracker.server;

import com.codahale.metrics.annotation.Timed;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.fw.common.mithra.AggregateList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Path("/portfolio")
@Produces(MediaType.APPLICATION_JSON)
public class PortfolioValueService {
    private static Logger logger = LoggerFactory.getLogger(PortfolioValueService.class.getName());

    private static final Function<Integer, SchemeValue> NEW_SCHEME = object -> new SchemeValue(SchemeFinder.findByPrimaryKey(object));

    @Path("transactions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactions() {
        return TransactionFinder.findMany(TransactionFinder.all());
    }

    @Path("today/{portfolio}")
    @GET
    @Timed
    public PortfolioSnapshot getPortfolioValue(@PathParam("portfolio") int portfolio) {
        LatestNAVDate latestNAVDate = LatestNAVDateFinder.findOne(LatestNAVDateFinder.all());

        Timestamp today = latestNAVDate.getDate();
        Timestamp priorDay = Timestamp.valueOf(today.toLocalDateTime().minusDays(1));
        logger.info("today : " + today);
        logger.info("priorDay : " + priorDay);
        PortfolioValue todayValue = calculatePortfolioValue(today, portfolio);
        PortfolioValue priorDayValue = calculatePortfolioValue(priorDay, portfolio);
        return new PortfolioSnapshot(todayValue, priorDayValue, new PortfolioValueOneDayDelta(todayValue, priorDayValue));
    }

    @Path("value/{date}/{portfolio}")
    @GET
    @Timed
    public PortfolioValue getPortfolioValue(@PathParam("date") String date, @PathParam("portfolio") int portfolio) {
        return calculatePortfolioValue(Timestamp.valueOf(date + " 00:00:00"), portfolio);
    }

    private PortfolioValue calculatePortfolioValue(Timestamp asOfDate, int portfolio) {
        TransactionList transactions;
        if (portfolio > 0) {
            transactions = PortfolioFinder.findByPrimaryKey(portfolio).getHoldings().getSchemes().getTransactions();
        } else {
            transactions = TransactionFinder.findMany(TransactionFinder.date().lessThanEquals(asOfDate));
        }

        MutableMap<Integer, SchemeValue> unitsBySchemes = UnifiedMap.newMap();
        double totalValue = 0.0;
        double totalCost = 0.0;

        for (Transaction transaction : transactions) {
            int schemeCode = transaction.getSchemeCode();
            SchemeValue schemeValue = unitsBySchemes.getIfAbsentPutWith(schemeCode, NEW_SCHEME, schemeCode);
            schemeValue.addTransaction(transaction);
        }

        List<SchemeValue> schemeValues = FastList.newList();
        for (Map.Entry<Integer, SchemeValue> entry : unitsBySchemes.entrySet()) {
            SchemeValue schemeValue = entry.getValue();
            calculateSchemeValue(schemeValue, asOfDate);

            schemeValues.add(schemeValue);
            totalValue += schemeValue.getValue();
            totalCost += schemeValue.getCost();
        }
        schemeValues.sort(Comparator.comparing(o -> o.getScheme().getName()));
        return new PortfolioValue(asOfDate, schemeValues, totalCost, totalValue);
    }

    private void calculateSchemeValue(SchemeValue schemeValue, Timestamp asOfDate) {
        Scheme scheme = schemeValue.getScheme();
        NetAssetValue navRecord = NetAssetValueFinder.findByPrimaryKey(scheme.getCode(), asOfDate);

        if (navRecord == null) {
            logger.info("NAV of scheme: " + scheme.getName() + " not found for date:" + asOfDate.toString());

            AggregateList navLoadAggregateData = new AggregateList(NetAssetValueFinder.schemeCode().eq(scheme.getCode())
                    .and(NetAssetValueFinder.date().lessThan(asOfDate)));
            navLoadAggregateData.addAggregateAttribute("latest", NetAssetValueFinder.date().max());
            asOfDate = navLoadAggregateData.get(0).getAttributeAsTimestamp("latest");
            navRecord = NetAssetValueFinder.findByPrimaryKey(scheme.getCode(), asOfDate);

            logger.info("Falling back to " + asOfDate.toString());
        }

        schemeValue.setLatestNAVDate(asOfDate);
        schemeValue.setNav(navRecord.getNetAssetValue());
        schemeValue.calculateXirr();
    }
}