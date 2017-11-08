package tracker.server;

import com.gs.collections.impl.list.mutable.FastList;
import tracker.domain.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class PortfolioValue {
    private Timestamp asOfDate;
    private List<SchemeValue> schemeValues;
    private double cost;
    private double value;

    public PortfolioValue(Timestamp asOfDate, List<SchemeValue> schemeValues, double cost, double value) {
        this.asOfDate = asOfDate;
        this.schemeValues = schemeValues;
        this.cost = cost;
        this.value = value;
    }

    public List<SchemeValue> getSchemeValues() {
        return schemeValues;
    }

    public double getValue() {
        return value;
    }

    public double getCost() {
        return cost;
    }

    public double getGains() {
        return value - cost;
    }

    public double getXirr() {
        List<Transaction> transactions = FastList.newList();
        schemeValues.forEach(schemeValue -> transactions.addAll(schemeValue.getTransactions()));

        return XIRR.calculate(transactions, getValue());
    }

    public double getReturnPercentage() {
        return 100 * getGains() / cost;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }
}
