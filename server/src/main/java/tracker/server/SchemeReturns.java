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

    public SchemeReturns(Scheme scheme) {
        this.scheme = scheme;
    }

    public void addReturn(String period, double value) {
        returns.put(period, value);
    }

    public Scheme getScheme() {
        return scheme;
    }

    public Map<String, Double> getReturns() {
        return returns;
    }
}
