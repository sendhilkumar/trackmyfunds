package tracker.server;

import tracker.domain.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XIRR {

    private static final double almostZero = 0.001;
    private static final long day = 24 * 60 * 60 * 1000;

    public static double calculate(List<Transaction> transactions, double value) {
        List<Double> costs = new ArrayList<>();
        List<Date> dates = new ArrayList<>();

        for (Transaction t : transactions) {
            dates.add(new Date(t.getDate().getTime()));
            costs.add(-t.getAmount());

            if (t.getDescription().toLowerCase().contains("reinvest")) {
                dates.add(new Date(t.getDate().getTime()));
                costs.add(t.getAmount());
            }
        }
        dates.add(new Date());
        costs.add(value);

        return new XIRR().newtonsMethod(costs, dates, 0.1);
    }

    private double newtonsMethod(List<Double> payments, List<Date> days, double guess) {
        double xirr = guess;
        double newXirr = 0.0;
        double err = 1e+100;

        while (err > almostZero) {
            newXirr = xirr - total_f_xirr(payments, days, xirr) / total_df_xirr(payments, days, xirr);
            err = Math.abs(newXirr - xirr);
            xirr = newXirr;
        }
        return xirr;
    }

    private double total_f_xirr(List<Double> payments, List<Date> dates, double xirr) {
        double result = 0.0;
        for (int i = 0; i < payments.size(); i++) {
            result = result + payments.get(i) * Math.pow((1.0 + xirr), dateDiff(dates.get(0), dates.get(i)) / 365.0);
        }
        return result;
    }

    private double total_df_xirr(List<Double> payments, List<Date> dates, double x) {
        double result = 0.0;
        for (int i = 0; i < payments.size(); i++) {
            double dateDiff = dateDiff(dates.get(0), dates.get(i));
            result = result + (1.0 / 365.0) * dateDiff * payments.get(i) * Math.pow((x + 1.0), ((dateDiff / 365.0) - 1.0));
        }
        return result;
    }

    private double dateDiff(Date d1, Date d2) {
        return (d1.getTime() - d2.getTime()) / day;
    }
}