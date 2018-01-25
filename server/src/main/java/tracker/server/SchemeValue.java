package tracker.server;

import com.gs.collections.api.tuple.Twin;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.tuple.Tuples;
import org.apache.commons.lang3.mutable.MutableDouble;
import tracker.domain.Scheme;
import tracker.domain.Transaction;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class SchemeValue {
    private Scheme scheme;

    private double units;
    private double nav;
    private Timestamp latestNAVDate;
    private double cost;
    private List<Transaction> transactions = FastList.newList();
    private double xirr;
    private List<ZTran> units_amount_cost_list = FastList.newList();

    public SchemeValue(Scheme scheme) {
        this.scheme = scheme;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public double getUnits() {
        return units;
    }

    public double getNav() {
        return nav;
    }

    public Timestamp getLatestNAVDate() {
        return latestNAVDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setNav(double nav) {
        this.nav = nav;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.units += transaction.getUnits();

        if (transaction.getAmount() > 0) {
            double cost = transaction.getAmount();
            if (transaction.getDescription().toLowerCase().contains("reinvest")) {
                cost = 0;
            }
            units_amount_cost_list.add(new ZTran(transaction.getUnits(), transaction.getAmount(), cost));
        }

        if (!transaction.getDescription().toLowerCase().contains("reinvest")) {
            if (transaction.getAmount() < 0) {
                double redeemedUnits = -transaction.getUnits();
                double originalCostOfRedeemedUnits = 0;

                for (ZTran zTran : units_amount_cost_list) {
                    if (redeemedUnits > 0) {
                        MutableDouble units = zTran.units;
                        MutableDouble amount = zTran.amount;
                        MutableDouble cost = zTran.cost;
                        if (units.getValue() > 0) {
                            double unitsToReduce = Math.min(redeemedUnits, units.getValue());
                            double amountPerUnit = amount.getValue() / units.getValue();
                            double amountToReduce = amountPerUnit * unitsToReduce;

                            units.add(-unitsToReduce);
                            redeemedUnits -= unitsToReduce;

                            amount.add(-amountToReduce);
                            if (cost.getValue() > 0) {
                                cost.add(-amountToReduce);
                                originalCostOfRedeemedUnits += amountToReduce;
                            }
                        }
                    }
                }
                this.cost -= originalCostOfRedeemedUnits;
            } else {
                this.cost += transaction.getAmount();
            }
        }
    }

    public void setLatestNAVDate(Timestamp latestNAVDate) {
        this.latestNAVDate = latestNAVDate;
    }

    public double getCost() {
        return cost;
    }

    public double getValue() {
        return units * nav;
    }

    public double getXirr() {
        return xirr;
    }

    public void calculateXirr() {
        this.xirr = XIRR.calculate(transactions, getValue());
    }

    private static class ZTran {
        private MutableDouble units;
        private MutableDouble amount;
        private MutableDouble cost;

        public ZTran(double units, double amount, double cost) {
            this.units = new MutableDouble(units);
            this.amount = new MutableDouble(amount);
            this.cost = new MutableDouble(cost);
        }
    }
}
