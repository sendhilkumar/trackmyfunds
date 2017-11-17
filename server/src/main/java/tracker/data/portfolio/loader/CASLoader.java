package tracker.data.portfolio.loader;

import com.gs.fw.common.mithra.MithraManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.data.portfolio.casparser.Parser;
import tracker.domain.SchemeFinder;
import tracker.domain.Transaction;
import tracker.domain.TransactionFinder;
import tracker.domain.TransactionList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CASLoader {
    private static Logger logger = LoggerFactory.getLogger(CASLoader.class.getName());

    public List<Transaction> loadStatement(File casPdf, String password) throws IOException {

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
        return transactionList;
    }
}