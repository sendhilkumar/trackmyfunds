package tracker.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NetAssetValue extends NetAssetValueAbstract {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public NetAssetValue() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public NetAssetValue(String schemeCode, String date, double netAssetValue, double repurchasePrice, double salePrice) {
        setSchemeCode(Integer.valueOf(schemeCode));
        setDate(Timestamp.valueOf(LocalDate.parse(date, formatter).atStartOfDay()));
        setNetAssetValue(getValidValue(netAssetValue));
        setRepurchasePrice(getValidValue(repurchasePrice));
        setSalePrice(getValidValue(salePrice));
    }

    private double getValidValue(double v) {
        return Double.isNaN(v) ? Double.MIN_VALUE : v;
    }

}
