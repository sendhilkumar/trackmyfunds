package tracker.data.funds.loader;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.attribute.Attribute;
import com.gs.fw.common.mithra.finder.MultiInOperation;
import com.gs.fw.common.mithra.util.MithraDataHolderTupleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.NetAssetValue;
import tracker.domain.NetAssetValueFinder;
import tracker.domain.NetAssetValueList;
import tracker.data.funds.navparser.HistoricalNAVParser;
import tracker.mithra.Mithra;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class HistoricalNAVLoader {
    private Logger logger = LoggerFactory.getLogger(HistoricalNAVLoader.class.getName());

    public static void main(String[] args) throws IOException {
        new HistoricalNAVLoader().load("2017-Oct-23", "2017-Oct-31");
    }

    public void load(String fromDate, String toDate) throws IOException {
        File file = new NAVDownloader().download(fromDate, toDate);
        loadNAVs(file);
    }

    private void loadNAVs(File file) throws IOException {
        Mithra.init();

        NetAssetValueList navRecords = new NetAssetValueList();
        Consumer<NetAssetValue> navRecordConsumer = navRecords::add;

        new HistoricalNAVParser().parseFile(file, navRecordConsumer);

        Attribute[] primaryKeyAttributes = NetAssetValueFinder.getPrimaryKeyAttributes();
        MithraDataHolderTupleSet tupleSet = new MithraDataHolderTupleSet(navRecords, primaryKeyAttributes);

        NetAssetValueList existing = NetAssetValueFinder.findMany(new MultiInOperation(primaryKeyAttributes, tupleSet));
        logger.info("Deleting existing records. count:" + existing.size());
        new NetAssetValueList(existing.asGscList()).deleteAllInBatches(100);

        MithraManager.getInstance().executeTransactionalCommand(tx -> {
            navRecords.insertAll();
            return null;
        });

        logger.info(navRecords.size() + " records inserted");
    }
}
