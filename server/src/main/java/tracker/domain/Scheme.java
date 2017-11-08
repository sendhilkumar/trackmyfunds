package tracker.domain;

public class Scheme extends SchemeAbstract {
    public Scheme() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public Scheme(String schemeCode, String schemeName, String amc) {
        this.setCode(Integer.valueOf(schemeCode));
        this.setName(schemeName);
        this.setAmc(amc);
    }

    @Override
    public String toString() {
        return "Scheme{code:" + getCode() + ";name:" + getName() + ";amc:" + getAmc();
    }
}
