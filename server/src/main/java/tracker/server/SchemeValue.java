package tracker.server;

import com.gs.collections.impl.list.mutable.FastList;
import tracker.domain.Scheme;
import tracker.domain.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchemeValue {
    private Scheme scheme;

    private double units;
    private double nav;
    private Timestamp latestNAVDate;
    private double cost;
    private List<Transaction> transactions = FastList.newList();
    private double xirr;

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
        if (!transaction.getDescription().toLowerCase().contains("reinvest")) {
            this.cost += transaction.getAmount();
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
}
