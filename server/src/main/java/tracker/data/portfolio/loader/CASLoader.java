package tracker.data.portfolio.loader;

import com.gs.fw.common.mithra.MithraManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.data.portfolio.casparser.Parser;
import tracker.domain.SchemeFinder;
import tracker.domain.Transaction;
import tracker.domain.TransactionFinder;
import tracker.domain.TransactionList;
import tracker.server.SchemesServices;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CASLoader {
    private static Logger logger = LoggerFactory.getLogger(CASLoader.class.getName());

    public List<Transaction> loadStatement(File casPdf, String password) throws IOException {

        SchemeFinderByName schemeFinderByName = new SchemeFinderByName(SchemeFinder.findMany(SchemeFinder.all()));

        List<Transaction> transactions = new Parser().parse(casPdf, password);
        logger.info("Read "+transactions.size() + " from "+casPdf);

        Map<String, Integer> schemeCodeCache = new HashMap<>();
        for (Transaction transaction : transactions) {
            String schemeName = transaction.getSchemeName();
            int schemeCode = schemeCodeCache.computeIfAbsent(schemeName, s -> schemeFinderByName.find(schemeName));
            transaction.setSchemeCode(schemeCode);
        }
        TransactionList transactionList = new TransactionList(transactions);

        logger.info("Beginning to insert transactions");

        MithraManager.getInstance().executeTransactionalCommand(tx -> {
            TransactionFinder.findMany(TransactionFinder.all()).deleteAll();
            transactionList.insertAll();
            return null;
        });

        logger.info(transactionList.size() + " transactions inserted");
        return transactionList;
    }
}