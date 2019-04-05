package ConfigUtils;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Diese Klasse stellt Methoden bereit um auf die Properties der uebergebenen Datei zu zugreifen
 * @author Alexander Huber 07.07.2018
 * @version 07.07.2018
 */
public class DatabaseCfgReader {

    public static String getUrl() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return getDriver()+":"+p.getProperty("url");
    }

    public static String getHostname() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("hostname");
    }

    public static String getDriver() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("driver");
    }

    public static String getPort() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("port");
    }

    public static String getUsername() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("username");
    }

    public static String getPassword() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("password");
    }

    /** Zur verwendung mehrerer Datenbanken bei sqlLite Server==Datenbank
    public static String getDatabase() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/database.properties"));
        p.load(b);
        b.close();
        return p.getProperty("database");
    }*/
}
