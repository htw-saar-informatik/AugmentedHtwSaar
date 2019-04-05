package ConfigUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Diese Klasse stellt Methoden bereit um auf die Properties der uebergebenen Datei zu zugreifen
 * @author Alexander Huber 07.07.2018
 * @version 07.07.2018
 */
public class ServerCfgReader {

    public static int getServerPort() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b;
        b = new BufferedInputStream(new FileInputStream("./config/server.properties"));
        p.load(b);
        b.close();
        String prop = p.getProperty("serverport");
        return Integer.parseInt(prop);
    }

    public static long getPeriodicTime() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/server.properties"));
        p.load(b);
        b.close();
        String prop = p.getProperty("msRefresh");
        return  Integer.parseInt(prop);
    }

}