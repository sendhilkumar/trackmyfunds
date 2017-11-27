package tracker.data.portfolio.casparser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.Scheme;
import tracker.domain.SchemeFinder;
import tracker.domain.Transaction;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static final String LINE_SEPARATOR = "\n";
    private static final String SPECIAL_TRANSACTION = "***";
    private static final String WORD_SEPARATOR = ";";
    private static final String CAS_UPLOAD = "cas upload";
    private static final DecimalFormat decimalFormat1 = new DecimalFormat("#,##0.0#;(#)");
    private static final DecimalFormat decimalFormat2 = new DecimalFormat("#,##0.0#;-#,##0.0#");
    private static final String FOLIO_NO = "Folio No:";
    private static final String REGISTRAR = "Registrar :";

    public List<Transaction> parse(File file, String password) throws IOException {
        logger.info("Loading " + file.toPath());
        List<Transaction> transactions = new ArrayList<>();

        PDDocument doc = PDDocument.load(file, password);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setAddMoreFormatting(true);
        pdfTextStripper.setShouldSeparateByBeads(true);
        pdfTextStripper.setLineSeparator(LINE_SEPARATOR);
        pdfTextStripper.setWordSeparator(WORD_SEPARATOR);
        pdfTextStripper.setSortByPosition(true);

        String text = pdfTextStripper.getText(doc);
        logger.info(text);

        String rtaCode = null;
        String schemeName = null;
        String folioNumber = null;
        String registrar = null;

        String[] lines = text.split(LINE_SEPARATOR);
        for (String line : lines) {
            if (line.contains(REGISTRAR)) { // || line.contains("Advisor")
//                logger.info(line);

                String[] words = line.trim().split(REGISTRAR);
                schemeName = words[0];
                rtaCode = schemeName.substring(0, line.indexOf('-'));
                registrar = words[1];

            } else if (line.contains(FOLIO_NO)) {
//                logger.info(line);

                String[] words = line.trim().split(WORD_SEPARATOR);
                folioNumber = words[0].substring(FOLIO_NO.length()).trim();

            } else if (!line.contains(SPECIAL_TRANSACTION)) {
                String[] words = line.trim().split(WORD_SEPARATOR);
                if (words.length > 0) {
                    String firstWord = words[0];
                    if (firstWord.matches("\\d\\d-...-\\d\\d\\d\\d")) {
                        if (words.length == 6) {
//                            logger.info(line);
                            //Date;Transaction;Amount;Units;Price;Unit Balance
                            String date = words[0];
                            String transaction = words[1];
                            double amount = parseDouble(words[2]);
                            double units = parseDouble(words[3]);
                            double price = parseDouble(words[4]);
                            double unitBalance = parseDouble(words[5]);
                            Transaction transactionEntry = new Transaction(date, transaction, amount, units, price, unitBalance,
                                    rtaCode, schemeName, folioNumber, registrar, CAS_UPLOAD);

                            transactions.add(transactionEntry);
                        }
                    }
                }
            }
        }

        return transactions;
    }

    private static double parseDouble(String word) {
        try {
            return decimalFormat1.parse(word).doubleValue();
        } catch (ParseException e1) {
            try {
               return decimalFormat2.parse(word).doubleValue();
            } catch (ParseException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

//    private static Map<String, Double> getNavMap() throws IOException {
//        Map<String, Double> navMap = new HashMap<>();
//        String navFile = "C:\\work\\projects\\mutualfunds\\tracker\\parser\\src\\main\\resources\\NAVOpen.txt";
//        List<String> lines = Files.readAllLines(new File(navFile).toPath());
//        for (String line : lines) {
//            if (line.contains(";")) {
//                //Scheme Code;ISIN Div Payout/ ISIN Growth;ISIN Div Reinvestment;Scheme Name;Net Asset Value;Repurchase Price;Sale Price;Date
//                String[] split = line.split(";");
//                String name = split[3];
//                try {
//                    Double nav = Double.valueOf(split[4].replaceAll(",", ""));
//                    navMap.put(name.replaceAll(" ", "").toUpperCase(), nav);
//                } catch (NumberFormatException e) {
//
//                }
//            }
//        }
//        return navMap;
//    }
}
