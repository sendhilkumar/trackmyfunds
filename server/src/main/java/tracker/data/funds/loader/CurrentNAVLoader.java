package tracker.data.funds.loader;

import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.utility.Iterate;
import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.util.MutableInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tracker.domain.*;
import tracker.data.funds.navparser.CurrentNAVParser;
import tracker.mithra.Mithra;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;

public class CurrentNAVLoader {
    private Logger logger = LoggerFactory.getLogger(CurrentNAVLoader.class.getName());

    //todo: holiday list or lookup
    //run this at 9:15PM on weekdays; whatever the max date in the data, assume that to be latest date;
    public static void main(String[] args) throws IOException {
        new CurrentNAVLoader().load();
    }

    public NAVLoad load() throws IOException {
        Timestamp currentTime = Timestamp.from(Instant.now());

        File download = new NAVDownloader().downloadCurrent();

        Mithra.init();

        final NetAssetValueList navRecords = new NetAssetValueList();
        UnifiedMap<Timestamp, MutableInteger> countMap = UnifiedMap.newMap();
        final Timestamp[] latestDate = {null};
        final Timestamp currentDate = currentTime;
        Consumer<NetAssetValue> navRecordConsumer = netAssetValue -> {
            navRecords.add(netAssetValue);
            Timestamp date = netAssetValue.getDate();
            countMap.getIfAbsentPutWith(date, object -> new MutableInteger(), null).add(1);
            if (!date.after(currentDate)) {
                if (latestDate[0] == null || date.after(latestDate[0])) {
                    latestDate[0] = date;
                }
            }
        };

        new CurrentNAVParser().parseFile(download, navRecordConsumer);

        Timestamp maxOccurringDate = Iterate.max(countMap.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
        final int[] dupeCount = {0};
        NAVLoad navLoadInfo = MithraManager.getInstance().executeTransactionalCommand(tx -> {
            NAVLoad navLoad = new NAVLoad();
            int navLoadId = navLoad.generateAndSetId();
            navLoad.setLoadTime(currentTime);
            navLoad.setLatestNavDate(latestDate[0]);
            navLoad.setMaxOccuringDate(maxOccurringDate);
            navLoad.insert();

            LatestNAVDate latestNAVDate = LatestNAVDateFinder.findOne(LatestNAVDateFinder.all());
            if (latestNAVDate != null) {
                latestNAVDate.delete();
            }

            LatestNAVDate newLatestNAVDate = new LatestNAVDate();
            newLatestNAVDate.setDate(maxOccurringDate);
            newLatestNAVDate.insert();
            for (NetAssetValue netAssetValue : navRecords) {
                NetAssetValue existing = NetAssetValueFinder.findByPrimaryKey(netAssetValue.getSchemeCode(), netAssetValue.getDate());
                if (existing != null) {
                    existing.delete();
                    dupeCount[0] += 1;
                }
            }
            navRecords.setLoadId(navLoadId);
            navRecords.insertAll();

            return navLoad;
        });

        logger.info("NAV load id:" + navLoadInfo.getId() +
                "\nNAV load time:" + navLoadInfo.getLoadTime() +
                "\nLatest NAV date:" + navLoadInfo.getLatestNavDate() +
                "\nMax occurring NAV date:" + navLoadInfo.getMaxOccuringDate()
        );
        logger.info(dupeCount[0] + " existing duplicate records inserted");
        logger.info(navRecords.size() + " records inserted");

        return navLoadInfo;
    }
}
