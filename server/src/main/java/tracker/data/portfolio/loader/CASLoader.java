package tracker.data.portfolio.loader;

import com.gs.fw.common.mithra.MithraManager;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.SchemeFinder;
import tracker.domain.Transaction;
import tracker.domain.TransactionFinder;
import tracker.domain.TransactionList;
import tracker.mithra.Mithra;
import tracker.data.portfolio.casparser.Parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CASLoader {
    private static Logger logger = LoggerFactory.getLogger(CASLoader.class.getName());

    private static String DOWNLOAD_DATA_DIR = "C:\\work\\deployment";

    public static void main(String[] args) throws IOException {
        String password = args[0];
        FileFilter pdf = new SuffixFileFilter("pdf");
        File[] files = new File(DOWNLOAD_DATA_DIR).listFiles(pdf);
        Arrays.sort(files, (o1, o2) -> (int) (o1.lastModified() - o2.lastModified()));
        File casPdf = files[files.length - 1];
        loadStatement(casPdf, password);
    }

    private static void loadStatement(File casPdf, String password) throws IOException {
        Mithra.init();
        SchemeFinderByName schemeFinderByName = new SchemeFinderByName(SchemeFinder.findMany(SchemeFinder.all()));

        List<Transaction> transactions = new Parser().parse(casPdf, password);
        for (Transaction transaction : transactions) {
            transaction.setSchemeCode(schemeFinderByName.find(transaction.getSchemeName()));
        }
        TransactionList transactionList = new TransactionList(transactions);

        MithraManager.getInstance().executeTransactionalCommand(tx -> {
            TransactionFinder.findMany(TransactionFinder.all()).deleteAll();
            transactionList.insertAll();
            return null;
        });

        logger.info(transactionList.size() + " transactions inserted");
    }
}