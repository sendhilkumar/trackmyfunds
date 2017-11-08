package tracker.mithra;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class Mithra {
    private Logger logger = LoggerFactory.getLogger(Mithra.class.getName());

    private static int maxTransactionTimeout = 120;

    public static void init() {
        Mithra app = new Mithra();
        try {
            app.initialiseMithra();
            app.loadMithraConfigurationXml("reladomo/config/ReladomoRuntimeConfig.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMithraConfigurationXml(String mithraXml) throws Exception {
        logger.info("Mithra configuration XML is " + mithraXml);
        InputStream is = Mithra.class.getClassLoader().getResourceAsStream(mithraXml);
        if (is == null) throw new Exception("can't find file: " + mithraXml + " in classpath");
        MithraManagerProvider.getMithraManager().readConfiguration(is);
        try {
            is.close();
        } catch (java.io.IOException e) {
            logger.error("Unable to initialise Reladomo!", e);
            throw new Exception("Unable to initialise Reladomo!", e);
        }
        logger.info("Reladomo configuration XML " + mithraXml + " is now loaded.");
    }

    private void initialiseMithra() throws Exception {
        try {
            logger.info("Transaction Timeout is " + maxTransactionTimeout);
            MithraManager mithraManager = MithraManagerProvider.getMithraManager();
            mithraManager.setTransactionTimeout(maxTransactionTimeout);
        } catch (Exception e) {
            logger.error("Unable to initialise Reladomo!", e);
            throw new Exception("Unable to initialise Reladomo!", e);
        }
        logger.info("Reladomo has been initialised!");
    }
}