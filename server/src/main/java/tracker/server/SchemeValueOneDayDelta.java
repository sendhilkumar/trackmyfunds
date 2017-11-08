package tracker.server;

import tracker.domain.Scheme;

import java.sql.Timestamp;

public class SchemeValueOneDayDelta {
    private Scheme scheme;

    private SchemeValue today;
    private SchemeValue prior;

    public SchemeValueOneDayDelta(SchemeValue today, SchemeValue prior) {
        this.scheme = today.getScheme();
        this.today = today;
        this.prior = prior;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public SchemeValue getToday() {
        return today;
    }

    public SchemeValue getPrior() {
        return prior;
    }
}
