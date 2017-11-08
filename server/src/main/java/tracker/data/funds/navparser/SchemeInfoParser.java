package tracker.data.funds.navparser;

import org.apache.commons.io.FileUtils;
import tracker.domain.Scheme;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class SchemeInfoParser {
    private static final String DELIMITER = ";";

    private final int schemeCodeIndex;
    private final int schemeNameIndex;

    private String currentAMC;

    public SchemeInfoParser(int schemeCodeIndex, int schemeNameIndex) {
        this.schemeCodeIndex = schemeCodeIndex;
        this.schemeNameIndex = schemeNameIndex;
    }

    public void parseFile(File file, Consumer<Scheme> recordConsumer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.openInputStream(file)));
        reader.readLine(); //header
        String line = reader.readLine();

        while (line != null) {
            processLine(line, recordConsumer);
            line = reader.readLine();
        }
    }

    private void processLine(String line, Consumer<Scheme> recordConsumer) {
        if (line.trim().length() > 0) {
            if (line.contains(DELIMITER)) {
                String[] strings = line.split(DELIMITER);
                String schemeCode = strings[schemeCodeIndex];
                String schemeName = strings[schemeNameIndex];
                Scheme scheme = new Scheme(schemeCode, schemeName, currentAMC);
                recordConsumer.accept(scheme);
            } else {
                currentAMC = line;
            }
        }
    }

}
