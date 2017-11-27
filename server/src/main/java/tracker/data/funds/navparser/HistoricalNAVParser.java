package tracker.data.funds.navparser;

import com.gs.collections.impl.set.mutable.UnifiedSet;
import com.gs.fw.common.mithra.bulkloader.SybaseIqDoubleFormatter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.NetAssetValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class HistoricalNAVParser {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final Pattern PATTERN = Pattern.compile(",");
    private static final String DELIMITER = ";";
    private Set<String> NA_STRINGS = UnifiedSet.newSetWith("N.A.", "-", "#DIV/0!","#N/A", "NA");

    public void parseFile(File file, Consumer<NetAssetValue> navRecordConsumer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.openInputStream(file)));
        reader.readLine(); //header
        String line = reader.readLine();

        while (line != null) {
            try {
                processLine(line, navRecordConsumer);
            } catch (NumberFormatException e) {
                logger.info(line);
            }
            line = reader.readLine();
        }
    }

    private void processLine(String line, Consumer<NetAssetValue> navRecordConsumer) {
        if (line.contains(DELIMITER)) {
            String[] strings = line.split(DELIMITER);

            String schemeCode = strings[0];
            double netAssetValue = toDouble(strings[2]);
            double repurchasePrice = toDouble(strings[3]);
            double salePrice = toDouble(strings[4]);
            String date = strings[5];

            NetAssetValue nav = new NetAssetValue(schemeCode, date, netAssetValue, repurchasePrice, salePrice);
            navRecordConsumer.accept(nav);
        }
    }

    private Double toDouble(String string) {
        if (NA_STRINGS.contains(string)) {
            return Double.NaN;
        }
        return Double.valueOf(PATTERN.matcher(string).replaceAll(""));
    }
}
