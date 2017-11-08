package tracker.data.funds.loader;

import com.gs.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.cache.SingleExtractorHashStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.Scheme;
import tracker.domain.SchemeFinder;
import tracker.domain.SchemeList;
import tracker.data.funds.navparser.SchemeInfoParser;
import tracker.mithra.Mithra;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class SchemeInfoLoader {
    private static Logger logger = LoggerFactory.getLogger(SchemeInfoLoader.class.getName());

    private static String DOWNLOAD_DATA_DIR = "data/src/main/resources/download/";

    public static void main(String[] args) throws IOException {

        Mithra.init();

        UnifiedSetWithHashingStrategy<Scheme> schemes = UnifiedSetWithHashingStrategy.newSet(new SingleExtractorHashStrategy(SchemeFinder.code()));
        Consumer<Scheme> recordConsumer = schemes::put;

        for (int year = 2017; year >= 2006; year--) {
            logger.info("Processing " + year);

            File file = new File(new File(DOWNLOAD_DATA_DIR), year + ".txt");

            new SchemeInfoParser(0, 1).parseFile(file, recordConsumer);
        }

        SchemeList schemeList = new SchemeList(schemes.toSortedListBy(SchemeFinder.code()));

        MithraManager.getInstance().executeTransactionalCommand(tx -> {
            schemeList.insertAll();
            return null;
        });

        logger.info("Schemes:" + schemeList.size());
    }
}