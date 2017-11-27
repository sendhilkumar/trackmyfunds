package tracker.data.funds.loader;

import com.gs.fw.common.mithra.MithraManager;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.GC;
import tracker.domain.NetAssetValue;
import tracker.domain.NetAssetValueFinder;
import tracker.domain.NetAssetValueList;
import tracker.data.funds.navparser.HistoricalNAVParser;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HistoricalNAVLoader {
    private Logger logger = LoggerFactory.getLogger(HistoricalNAVLoader.class.getName());
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public List<String> load(String f) throws IOException {
        DateTime end = DateTime.parse("2017-11-23T00:00");
        DateTime monthStart = DateTime.parse(f+"T00:00");

        DateTime monthEnd = monthStart.plusMonths(1).minusDays(1);
        List<String> result = new ArrayList<>();

        while (monthEnd.isBefore(end)) {
            String fromDate = dateTimeFormatter.print(monthStart);
            String toDate = dateTimeFormatter.print(monthEnd);
            logger.info("Loading NAVs " + fromDate + " to " + toDate);
            int loadSize = load(fromDate, toDate);
            result.add(fromDate + " to " + toDate + ":" + loadSize);
            monthStart = monthStart.plusMonths(1);
            monthEnd = monthStart.plusMonths(1).minusDays(1);
        }

        return result;
    }

    public int load(String fromDate, String toDate) throws IOException {
        int retryCount = 0;
        File file = null;
        boolean success = false;
        while (!success && retryCount < 2) {
            try {
                file = new NAVDownloader().download(fromDate, toDate);
                success = true;
            } catch (Exception e) {
                retryCount++;
            }
        }

        NetAssetValueList navRecords = new NetAssetValueList();
        Consumer<NetAssetValue> navRecordConsumer = navRecords::add;

        new HistoricalNAVParser().parseFile(file, navRecordConsumer);

        NetAssetValueList existing = NetAssetValueFinder.findMany(
                NetAssetValueFinder.date().greaterThanEquals(Timestamp.valueOf(fromDate + " 00:00:00"))
                        .and(NetAssetValueFinder.date().lessThanEquals(Timestamp.valueOf(toDate + " 00:00:00"))));

        logger.info("Deleting existing records. count:" + existing.size());
        new NetAssetValueList(existing.asGscList()).deleteAllInBatches(100);

        MithraManager.getInstance().executeTransactionalCommand(tx -> {
            navRecords.insertAll();
            return null;
        });

        logger.info(navRecords.size() + " records inserted");
        return navRecords.size();
    }

}
