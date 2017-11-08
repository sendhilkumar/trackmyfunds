package tracker.server;

import com.codahale.metrics.annotation.Timed;
import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.data.funds.loader.CurrentNAVLoader;
import tracker.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Path("/schemes")
@Produces(MediaType.APPLICATION_JSON)
public class TrackerService {
    private final SchemeIndex schemeIndex;

    public TrackerService() {
        schemeIndex = new SchemeIndex(SchemeFinder.findMany(SchemeFinder.all()));
    }

    @Path("loadCurrentNAV")
    @POST
    @Timed
    public NAVLoad loadCurrentNAV() throws IOException {
        return new CurrentNAVLoader().load();
    }


    @Path("find/{schemeName}")
    @GET
    @Timed
    public List<Scheme> find(@PathParam("schemeName") String schemeName) throws IOException, ParseException {
        return schemeIndex.findSchemes(schemeName);
    }

    @Path("history/{schemeCode}")
    @GET
    @Timed
    public List<NetAssetValue> getNAVHistory(@PathParam("schemeCode") int schemeCode) {
        NetAssetValueList netAssetValues = NetAssetValueFinder.findMany(NetAssetValueFinder.schemeCode().eq(schemeCode)
                .and(NetAssetValueFinder.date().greaterThan(Timestamp.valueOf("2017-01-01 00:00:00"))));
        netAssetValues.setOrderBy(NetAssetValueFinder.date().ascendingOrderBy());
        return netAssetValues;
    }

    @Path("history/{schemeCode}/{returnPeriods}")
    @GET
    @Timed
    public SchemeReturns getNAVHistory(@PathParam("schemeCode") int schemeCode, @PathParam("returnPeriods") String returnPeriods) {
        String[] periods = returnPeriods.split(",");

        SchemeReturns returns = new SchemeReturns(SchemeFinder.findByPrimaryKey(schemeCode));

        for (String period : periods) {
            ReturnPeriod returnPeriod = period(period.trim());

            NetAssetValue fromNAV = getNetAssetValue(schemeCode, returnPeriod.from, 1, 0);
            NetAssetValue toNAV = getNetAssetValue(schemeCode, returnPeriod.to, -1, 0);

            if (returnPeriod.numYears > 1) {
                double cagr = 100 * (Math.pow(toNAV.getNetAssetValue() / fromNAV.getNetAssetValue(), 1 / returnPeriod.numYears) - 1);
                returns.addReturn(period, cagr);
            } else {
                double pct = 100 * (toNAV.getNetAssetValue() - fromNAV.getNetAssetValue()) / fromNAV.getNetAssetValue();
                returns.addReturn(period, pct);
            }
        }

        return returns;
    }

    private NetAssetValue getNetAssetValue(int schemeCode, Timestamp from, int amountToAdd, int fallbackCount) {
        NetAssetValue fromNAV = NetAssetValueFinder.findByPrimaryKey(schemeCode, from);
        if (fromNAV == null) {
            if (fallbackCount < 10) {
                from = Timestamp.valueOf(from.toLocalDateTime().plus(amountToAdd, ChronoUnit.DAYS));
                return getNetAssetValue(schemeCode, from, amountToAdd, fallbackCount + 1);
            } else {
                throw new RuntimeException("Cant find NAV of scheme " + schemeCode + " as of " + from.toString());
                //todo: check scheme start date
            }
        }
        return fromNAV;
    }

    private ReturnPeriod period(String period) {
        if (period.endsWith("d") || period.endsWith("w") || period.endsWith("m") || period.endsWith("y")) {
            Timestamp today = LatestNAVDateFinder.findOne(LatestNAVDateFinder.all()).getDate();

            String type = period.substring(period.length() - 1);
            TemporalUnit unit;
            double divideBy = 1;

            Integer length = Integer.valueOf(period.substring(0, period.length() - 1));
            switch (type) {
                case "d": {
                    unit = ChronoUnit.DAYS;
                    break;
                }
                case "w": {
                    unit = ChronoUnit.WEEKS;
                    break;
                }
                case "m": {
                    unit = ChronoUnit.MONTHS;
                    break;
                }
                case "y": {
                    unit = ChronoUnit.YEARS;
                    divideBy = length;
                    break;
                }
                default:
                    throw new RuntimeException("Unknown time unit " + type);
            }

            Timestamp from = Timestamp.valueOf(today.toLocalDateTime().minus(length, unit));
            return new ReturnPeriod(from, today, divideBy);
        }

        if (period.contains("to")) {
            String[] strings = period.split("to");
            Timestamp from = Timestamp.valueOf(strings[0] + " 00:00:00");
            Timestamp to = Timestamp.valueOf(strings[1] + " 00:00:00");

            Period between = Period.between(from.toLocalDateTime().toLocalDate(), to.toLocalDateTime().toLocalDate());
            double divideBy = 1;
            if (between.getYears() > 1) {
                int days = between.getYears() * 365 +
                        between.getMonths() * 30 +
                        between.getDays();
                divideBy = days / 365;
            }
            return new ReturnPeriod(from, to, divideBy);
        }

        if (period.length() == 4) { //year
            Timestamp from = Timestamp.valueOf(period + "-01-01 00:00:00");
            Timestamp to = Timestamp.valueOf(period + "-12-31 00:00:00");
            if (to.after(Timestamp.from(Instant.now()))) {
                to = Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay());
            }
            return new ReturnPeriod(from, to, 1);
        }

        throw new RuntimeException("Unknown period " + period);
    }

    private static class ReturnPeriod {
        private final Timestamp from;
        private final Timestamp to;
        private final double numYears;

        private ReturnPeriod(Timestamp from, Timestamp to, double numYears) {
            this.from = from;
            this.to = to;
            this.numYears = numYears;
        }
    }

}