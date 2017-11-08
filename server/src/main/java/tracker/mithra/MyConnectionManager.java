package tracker.mithra;

import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.bulkloader.BulkLoaderException;
import com.gs.fw.common.mithra.connectionmanager.SourcelessConnectionManager;
import com.gs.fw.common.mithra.connectionmanager.XAConnectionManager;
import com.gs.fw.common.mithra.databasetype.DatabaseType;
import com.gs.fw.common.mithra.databasetype.PostgresDatabaseType;

import java.sql.Connection;
import java.util.TimeZone;

public class MyConnectionManager implements SourcelessConnectionManager {
    protected static MyConnectionManager instance;
    protected static final String MAX_POOL_SIZE_KEY = "maxPoolSize";
    protected final int DEFAULT_MAX_WAIT = 500;
    protected static final int DEFAULT_POOL_SIZE = 10;
    private static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT+05:30");
    private final String driverClassname = "org.postgresql.Driver";
    private final String serverName = "postgresql";
    private final String resourceName = "public";

    private XAConnectionManager xaConnectionManager;

    public static synchronized MyConnectionManager getInstance() {
        if (instance == null) {
            instance = new MyConnectionManager();
        }
        return instance;
    }

    protected MyConnectionManager() {
        this.createConnectionManager();
    }

    private XAConnectionManager createConnectionManager() {
        xaConnectionManager = new XAConnectionManager();
        xaConnectionManager.setDriverClassName(driverClassname);
        xaConnectionManager.setMaxWait(DEFAULT_MAX_WAIT);
        xaConnectionManager.setLdapName(serverName);
        xaConnectionManager.setDefaultSchemaName(resourceName);
        xaConnectionManager.setJdbcUser("todo");
        xaConnectionManager.setJdbcPassword("todo");
        xaConnectionManager.setPoolName("trackmyfunds connection pool");
        xaConnectionManager.setInitialSize(1);
        xaConnectionManager.setPoolSize(DEFAULT_POOL_SIZE);
        xaConnectionManager.setJdbcConnectionString("jdbc:postgresql://mftracker.ccxr0nuqeqzb.ap-south-1.rds.amazonaws.com:5432/mftrackerdb");
        xaConnectionManager.initialisePool();
        return xaConnectionManager;
    }

    public Connection getConnection() {
        return xaConnectionManager.getConnection();
    }

    public DatabaseType getDatabaseType() {
        return PostgresDatabaseType.getInstance();
    }

    public TimeZone getDatabaseTimeZone() {
        return TIMEZONE;
    }

    public BulkLoader createBulkLoader() throws BulkLoaderException {
        return this.getDatabaseType().createBulkLoader(
                "sa",
                "",
                this.xaConnectionManager.getHostName(),
                this.xaConnectionManager.getPort());
    }

    public String getDatabaseIdentifier() {
        return xaConnectionManager.getServerName() + ":" + xaConnectionManager.getResourceName();
    }
}