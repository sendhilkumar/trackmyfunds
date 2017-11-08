package tracker.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction extends TransactionAbstract {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public Transaction() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public Transaction(String date, String description, double amount, double units, double price, double unitBalance,
                       String rtaCode, String schemeName, String folioNumber, String registrar, String source) {

        setDate(Timestamp.valueOf(LocalDate.parse(date, formatter).atStartOfDay()));
        setDescription(description);
        setAmount(amount);
        setUnits(units);
        setPrice(price);
        setUnitBalance(unitBalance);
        setRtaCode(rtaCode);
        setSchemeName(schemeName);
        setFolioNumber(folioNumber);
        setRegistrar(registrar);
        setSource(source);
    }

    @Override
    public String toString() {
        return '\n' + getSchemeName() + ';' +
                getDate() + "; " +
                getDescription() + "; " +
                getAmount() + "; " +
                getUnits() + "; " +
                getPrice() + "; " +
                getUnitBalance() + "; " +
                getRtaCode() + "; " +
                getSchemeName() + "; " +
                getFolioNumber() + "; " +
                getRegistrar() + "; " +
                '\n';
    }
}