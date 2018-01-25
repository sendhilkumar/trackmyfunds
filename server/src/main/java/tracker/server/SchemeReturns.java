package tracker.server;

import com.gs.collections.impl.map.mutable.UnifiedMap;
import tracker.domain.Scheme;

import java.util.Map;

/**
 * 1d, 1w, 1m, 1y, 2y,...
 * 2017, 2016, 2015,...
 * from yyyymmdd to yyyymmdd
 */
public class SchemeReturns {
    private Scheme scheme;
    private Map<String, Double> returns = UnifiedMap.newMap();
    private Map<String, Double> volatility = UnifiedMap.newMap();

    public SchemeReturns(Scheme scheme) {
        this.scheme = scheme;
    }

    public void addReturn(String period, double value) {
        returns.put(period, value);
    }

    public void addVolatility(String period, double value) {
        volatility.put(period, value);
    }

    public Scheme getScheme() {
        return scheme;
    }

    public Map<String, Double> getReturns() {
        return returns;
    }

    public Map<String, Double> getVolatility() {
        return volatility;
    }
}
