package tracker.server;

import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.utility.ListIterate;

import java.util.List;
import java.util.Map;

public class PortfolioValueOneDayDelta {
    private List<SchemeValueOneDayDelta> schemeValues;
    private PortfolioValue today;
    private PortfolioValue prior;

    public PortfolioValueOneDayDelta(PortfolioValue today, PortfolioValue prior) {
        this.today = today;
        this.prior = prior;
        schemeValues = FastList.newList();

        Map<Integer, SchemeValue> priorDaySchemeValues = UnifiedMap.newMap();
        ListIterate.forEach(prior.getSchemeValues(), (Procedure<SchemeValue>) each -> priorDaySchemeValues.put(each.getScheme().getCode(), each));

        for (SchemeValue todaySchemeValue : today.getSchemeValues()) {
            SchemeValue priorDaySchemeValue = priorDaySchemeValues.get(todaySchemeValue.getScheme().getCode());
            schemeValues.add(new SchemeValueOneDayDelta(todaySchemeValue, priorDaySchemeValue));
        }
    }

    public PortfolioValue getToday() {
        return today;
    }

    public PortfolioValue getPrior() {
        return prior;
    }

    public List<SchemeValueOneDayDelta> getSchemeValues() {
        return schemeValues;
    }
}
