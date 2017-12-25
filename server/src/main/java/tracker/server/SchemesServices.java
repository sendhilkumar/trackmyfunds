package tracker.server;

import com.codahale.metrics.annotation.Timed;
import com.gs.collections.api.RichIterable;
import com.gs.collections.api.multimap.list.MutableListMultimap;
import com.gs.collections.api.tuple.Pair;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.set.mutable.UnifiedSet;
import org.apache.lucene.queryparser.classic.ParseException;
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
import java.util.Map;
import java.util.Set;

@Path("/schemes")
@Produces(MediaType.APPLICATION_JSON)
public class SchemesServices {
    private final SchemeIndex schemeIndex;
    private final Map<Integer, Map<Timestamp, NetAssetValue>> navCache = UnifiedMap.newMap();

    public SchemesServices() {
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

    @Path("returns/{schemeCode}/{returnPeriods}")
    @GET
    @Timed
    public SchemeReturns getReturns(@PathParam("schemeCode") int schemeCode, @PathParam("returnPeriods") String returnPeriods) {
        String[] periods = returnPeriods.split(",");
        Scheme scheme = SchemeFinder.findByPrimaryKey(schemeCode);
        return getSchemeReturns(periods, scheme, 0);
    }

    private SchemeReturns getSchemeReturns(String[] periods, Scheme scheme, int fallbackCounter) {
        SchemeReturns returns = new SchemeReturns(scheme);

        for (String period : periods) {
            ReturnPeriod returnPeriod = returnPeriod(period.trim());
            NetAssetValue fromNAV = getNetAssetValue(scheme.getCode(), returnPeriod.from, 1, fallbackCounter);
            NetAssetValue toNAV = getNetAssetValue(scheme.getCode(), returnPeriod.to, -1, fallbackCounter);
            double ret = calcReturns(returnPeriod, fromNAV, toNAV);
            if (!Double.isNaN(ret)) {
                returns.addReturn(period, ret);
            }
        }

        return returns;
    }

    private double calcReturns(ReturnPeriod returnPeriod, NetAssetValue fromNAV, NetAssetValue toNAV) {
        double ret = Double.NaN;

        if (fromNAV != null && toNAV != null) {
            if (returnPeriod.numYears > 1) {
                ret = 100 * (Math.pow(toNAV.getNetAssetValue() / fromNAV.getNetAssetValue(), 1 / returnPeriod.numYears) - 1);
            } else {
                ret = 100 * (toNAV.getNetAssetValue() - fromNAV.getNetAssetValue()) / fromNAV.getNetAssetValue();
            }
        }
        return ret;
    }

    @Path("returns/all/{returnPeriods}")
    @GET
    @Timed
    public List<SchemeReturns> getReturns(@PathParam("returnPeriods") String returnPeriods) {
        String[] periods = returnPeriods.split(",");
        List<ReturnPeriod> returnPeriodList = FastList.newList();
        for (String period : periods) {
            returnPeriodList.add(returnPeriod(period.trim()));
        }

        SchemeList schemes = SchemeFinder.findMany(SchemeFinder.all());
        List<SchemeReturns> allReturns = FastList.newList();
        for (Scheme scheme : schemes) {
            int schemeCode = scheme.getCode();
            SchemeReturns returns = new SchemeReturns(scheme);

            for (ReturnPeriod returnPeriod : returnPeriodList) {
                NetAssetValue fromNAV = getNetAssetValue(schemeCode, returnPeriod.from, 1, 0);
                NetAssetValue toNAV = getNetAssetValue(schemeCode, returnPeriod.to, -1, 0);

                double ret = calcReturns(returnPeriod, fromNAV, toNAV);
                if (!Double.isNaN(ret)) {
                    returns.addReturn(returnPeriod.period, ret);
                }
            }
            allReturns.add(returns);
        }
        return allReturns;
    }

    @Path("top")
    @GET
    @Timed
    public List<SchemeReturns> getTopSchemes(@QueryParam("criteria") String criteria) {
        String criterias = criteria != null ? criteria : "3y>=12,2y>=20,1y>=20,1m>=4";
        String returnPeriodsToDisplay = "5y,4y,3y,2y,1y,6m,1m,2w,1w,1d";

        return getSchemes(criterias, returnPeriodsToDisplay);
    }

    @Path("filter/{criterias}/{returnPeriods}")
    @GET
    @Timed
    public List<SchemeReturns> getSchemes(@PathParam("criterias") String criterias, @PathParam("returnPeriods") String returnPeriods) {
        List<SchemeReturns> schemesSatisfyingCriteria = FastList.newList();
        String[] periods = returnPeriods.split(",");

        List<ReturnCriteria> criteriaList = FastList.newList();
        for (String criteria : criterias.split(",")) {
            criteriaList.add(returnCriteria(criteria.trim()));
        }

        cacheNavs(periods, criteriaList);

        SchemeList schemes = SchemeFinder.findMany(
                SchemeFinder.category().in(UnifiedSet.newSetWith("Growth", "ELSS", "Balanced"))
                        .and(SchemeFinder.closed().eq("N"))
                        .and(SchemeFinder.name().contains("rowth"))
//                        .and(SchemeFinder.name().contains("egular"))
                        .and(SchemeFinder.name().notContains("onus"))
                        .and(SchemeFinder.name().notContains("ividend"))
                        .and(SchemeFinder.isinGrowth().isNotNull())
                        .and(SchemeFinder.isinReinvestment().isNull())
        );

        for (Scheme scheme : schemes) {
            boolean satisfied = true;
            int schemeCode = scheme.getCode();

            for (ReturnCriteria returnCriteria : criteriaList) {
                if (satisfied) {
                    ReturnPeriod returnPeriod = returnCriteria.returnPeriod;

                    NetAssetValue fromNAV = getNetAssetValue(schemeCode, returnPeriod.from, 1, 10);
                    NetAssetValue toNAV = getNetAssetValue(schemeCode, returnPeriod.to, -1, 10);

                    if (fromNAV != null && toNAV != null) {
                        double ret;
                        if (returnPeriod.numYears > 1) {
                            ret = 100 * (Math.pow(toNAV.getNetAssetValue() / fromNAV.getNetAssetValue(), 1 / returnPeriod.numYears) - 1);
                        } else {
                            ret = 100 * (toNAV.getNetAssetValue() - fromNAV.getNetAssetValue()) / fromNAV.getNetAssetValue();
                        }
                        if (!returnCriteria.satisfies(ret)) {
                            satisfied = false;
                        }
                    } else {
                        satisfied = false;
                    }
                }
            }
            if (satisfied) {
                SchemeReturns schemeReturns = getSchemeReturns(periods, scheme, 10);
                schemesSatisfyingCriteria.add(schemeReturns);
            }
        }

        return schemesSatisfyingCriteria;
    }

    private void cacheNavs(String[] periods, List<ReturnCriteria> criteriaList) {
        Set<Timestamp> dates = UnifiedSet.newSet();

        for (String period : periods) {
            ReturnPeriod returnPeriod = returnPeriod(period.trim());
            candidateDates(dates, returnPeriod.from, 1);
            candidateDates(dates, returnPeriod.to, -1);
        }

        for (ReturnCriteria returnCriteria : criteriaList) {
            ReturnPeriod returnPeriod = returnCriteria.returnPeriod;
            candidateDates(dates, returnPeriod.from, 1);
            candidateDates(dates, returnPeriod.to, -1);
        }

        NetAssetValueList netAssetValues = NetAssetValueFinder.findMany(NetAssetValueFinder.date().in(dates));
        MutableListMultimap<Integer, NetAssetValue> bySchemeCode = netAssetValues.asGscList().groupBy(NetAssetValueFinder.schemeCode());
        RichIterable<Pair<Integer, RichIterable<NetAssetValue>>> keyMultiValuePairsView = bySchemeCode.keyMultiValuePairsView();

        for (Pair<Integer, RichIterable<NetAssetValue>> group : keyMultiValuePairsView) {
            Integer schemeCode = group.getOne();

            Map<Timestamp, NetAssetValue> values;
            if (!navCache.containsKey(schemeCode)) {
                values = UnifiedMap.newMap();
                navCache.put(schemeCode, values);
            } else {
                values = navCache.get(schemeCode);
            }
            for (NetAssetValue nav : group.getTwo()) {
                values.put(nav.getDate(), nav);
            }
        }
    }

    private void cacheNav(NetAssetValue netAssetValue) {
        int schemeCode = netAssetValue.getSchemeCode();

        Map<Timestamp, NetAssetValue> values;
        if (!navCache.containsKey(schemeCode)) {
            values = UnifiedMap.newMap();
            navCache.put(schemeCode, values);
        } else {
            values = navCache.get(schemeCode);
        }
        values.put(netAssetValue.getDate(), netAssetValue);
    }

    private ReturnCriteria returnCriteria(String criteria) {
        int index = criteria.indexOf("<=");
        if (index == -1) {
            index = criteria.indexOf(">=");
        }
        if (index == -1) {
            throw new RuntimeException("Should have >= or <= condition. Invalid criteria: " + criteria);
        }

        ReturnPeriod returnPeriod = returnPeriod(criteria.substring(0, index));
        String condition = criteria.substring(index, index + 2);
        Double value = Double.valueOf(criteria.substring(index + 2));

        return new ReturnCriteria(returnPeriod, condition, value);
    }

    private void candidateDates(Set<Timestamp> dates, Timestamp date, int fallbackDirection) {
        Timestamp nextDate = date;
        dates.add(date);
        for (int i = 1; i < 10; i++) {
            nextDate = Timestamp.valueOf(nextDate.toLocalDateTime().plus(fallbackDirection, ChronoUnit.DAYS));
            dates.add(nextDate);
        }
    }

    private NetAssetValue getNetAssetValue(int schemeCode, Timestamp from, int fallbackDirection, int fallbackCounter) {
        NetAssetValue netAssetValue = null;

        Map<Timestamp, NetAssetValue> cacheScheme = navCache.get(schemeCode);
        if (cacheScheme != null) {
            netAssetValue = cacheScheme.get(from);
        }
        if (netAssetValue == null && fallbackCounter < 10) {
            netAssetValue = NetAssetValueFinder.findByPrimaryKey(schemeCode, from);

            if (netAssetValue == null) {
                from = Timestamp.valueOf(from.toLocalDateTime().plus(fallbackDirection, ChronoUnit.DAYS));
                return getNetAssetValue(schemeCode, from, fallbackDirection, fallbackCounter + 1);
            } else {
                cacheNav(netAssetValue);
            }
        }
        return netAssetValue;
    }

    private ReturnPeriod returnPeriod(String period) {
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
            return new ReturnPeriod(period, from, today, divideBy);
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
            return new ReturnPeriod(period, from, to, divideBy);
        }

        if (period.length() == 4) { //year
            Timestamp from = Timestamp.valueOf(period + "-01-01 00:00:00");
            Timestamp to = Timestamp.valueOf(period + "-12-31 00:00:00");
            if (to.after(Timestamp.from(Instant.now()))) {
                to = Timestamp.valueOf(Timestamp.from(Instant.now()).toLocalDateTime().toLocalDate().atStartOfDay());
            }
            return new ReturnPeriod(period, from, to, 1);
        }

        throw new RuntimeException("Unknown period " + period);
    }

    private static class ReturnPeriod {
        private final String period;
        private final Timestamp from;
        private final Timestamp to;
        private final double numYears;

        private ReturnPeriod(String period, Timestamp from, Timestamp to, double numYears) {
            this.period = period;
            this.from = from;
            this.to = to;
            this.numYears = numYears;
        }
    }

    private static class ReturnCriteria {
        private final ReturnPeriod returnPeriod;
        private String condition; //<=, >=
        private final double value;

        private ReturnCriteria(ReturnPeriod returnPeriod, String condition, double value) {
            this.returnPeriod = returnPeriod;
            this.condition = condition;
            this.value = value;
        }

        public boolean satisfies(double ret) {
            if (condition.equals("<=")) {
                return ret <= value;
            } else {
                return ret >= value;
            }
        }
    }
}