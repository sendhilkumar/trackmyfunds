package tracker.data.funds.navparser;

import org.apache.commons.io.FileUtils;
import tracker.domain.NetAssetValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CurrentNAVParser {
    private static final String DELIMITER = ";";

    public void parseFile(File file, Consumer<NetAssetValue> navRecordConsumer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.openInputStream(file)));
        reader.readLine(); //header
        String line = reader.readLine();

        while (line != null) {
            processLine(line, navRecordConsumer);
            line = reader.readLine();
        }
    }

    //0 Scheme Code;
    //1 ISIN Div Payout/ ISIN Growth;
    //2 ISIN Div Reinvestment;
    //3 Scheme Name;
    //4 Net Asset Value;
    //5 Repurchase Price;
    //6 Sale Price;
    //7 Date
    private void processLine(String line, Consumer<NetAssetValue> navRecordConsumer) {
        if (line.contains(DELIMITER)) {
            String[] strings = line.split(DELIMITER);
            String schemeCode = strings[0];
//            Scheme scheme = new Scheme(schemeCode, strings[3]);
            double netAssetValue = toDouble(strings[4]);
            double repurchasePrice = toDouble(strings[5]);
            double salePrice = toDouble(strings[6]);
            String date = strings[7];

            NetAssetValue nav = new NetAssetValue(schemeCode, date, netAssetValue, repurchasePrice, salePrice);
            navRecordConsumer.accept(nav);
        }
    }

    private Double toDouble(String string) {
        if ("N.A.".equals(string)) {
            return Double.NaN;
        } else {
            return Double.valueOf(string);
        }
    }
}
