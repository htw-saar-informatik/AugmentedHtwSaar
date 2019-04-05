package ConfigUtils;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Diese Klasse stellt Methoden bereit um auf die Properties der uebergebenen Datei zu zugreifen
 * @author Alexander Huber 07.07.2018
 * @version 20.08.2018
 */
public class TableCfgReader {

    public static String createUser() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/tables.properties"));
        p.load(b);
        b.close();
        return p.getProperty("user");
    }

    public static String createRoom() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/tables.properties"));
        p.load(b);
        b.close();
        return p.getProperty("room");
    } 
    
    public static String createBlackboard() throws Exception {
        Properties p = new Properties();
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("./config/tables.properties"));
        p.load(b);
        b.close();
        return p.getProperty("blackboard");
    }
}
