package DatabaseUtils;

import ConfigUtils.DatabaseCfgReader;
import Constants.DatabaseConstants;
import locals.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Diese Klasse regelt die Verbindung mit der Datenbank
 * @author Alexander Huber, Marco Becker
 * @version 02.08.2018
 */
public class DatabaseConnector {

    private String url;
    private String username;
    private String password;
    //private String database;
    private static Connection connection;

    /**
     * Stellt eine Verbindung mit der sqlite Datenbank auf verwendung der Properties-Datei in den ConfigUtils
     * @return Gibt das Ergebniss der Verbindung zurueck‚
     * @throws Exception
     */
    public String connect() throws Exception {
        this.url = DatabaseCfgReader.getUrl();
        this.username = DatabaseCfgReader.getUsername();
        this.password = DatabaseCfgReader.getPassword();

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url,username,password);
        if(connection.isValid(DatabaseConstants.DATABASE_TIMEOUT)) {
            return MessagesEN.DATABASE_CONNECTED_1+MessagesEN.DATABASE_CONNECTED_2+url+MessagesEN.DATABASE_CONNECTED_3;
        }
        return ErrorsEN.DATABASE_CONNECTION_FAILED;
    }

    /**
     * Schließt die Verbindung zu der Datenbank
     * @throws Exception
     */
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Da SqlLite keine Datenbank besitzt, sondern nur eine Datei ist, muss diese Gelöscht werden
     * @throws Exception
     */
    public void RemoveDatabase() throws Exception {
        this.close();
        Path path = FileSystems.getDefault().getPath(DatabaseCfgReader.getHostname());
        try{
            Files.delete(path);
        }catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        }catch (IOException x) {
            System.err.println(x);
        }
    }

    /**
     * Prüft ob eine Verbindung zu der Datenbank besteht
     * @return Wert des Ergebnis
     * @throws Exception
     */
    public boolean isConnected() throws Exception {
        if(connection.isValid(DatabaseConstants.DATABASE_TIMEOUT)) {
            return true;
        }
        return false;
    }

    public static synchronized Connection getConnection() {
        return connection;
    }
}

