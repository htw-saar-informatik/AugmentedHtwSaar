package Server;

import DatabaseUtils.DatabaseConnector;
import ConfigUtils.ServerCfgReader;
import ConfigUtils.TableCfgReader;

import Service.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Server der die Kommunikation zwischen Datenbank und Clients bereitstellt
 * @author Alexander Huber 07.07.2018
 * @version 06.08.2018
 */
public class Server {

    //Config variables
    private int port;

    public Server() throws Exception {
        this.port = ServerCfgReader.getServerPort();
    }
    /**
     * Kontruktor zum initialisieren des Servers
     * @throws Exception
     */
   /* public Server() throws Exception {
        this.port = ServerCfgReader.getServerPort();
        DatabaseConnector db = new DatabaseConnector();
        String result = db.connect();
        ResultSet rs = DatabaseConnector.getConnection().getMetaData().getTables(null, null, "%", null);
        if (!rs.next() ) {
            result = db.connect();
            DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createUser());
            DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createRoom());
            DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createBlackboard());
        }else{
            while (rs.next()) {
                result = rs.getString(3);
                if(!(//result.equals("Angestellter") |
                        //result.equals("Mitarbeiter")|
                        //result.equals("Professor")|
                        result.equals("Raum")|
                        //result.equals("Student")|
                        result.equals("User")|
                        result.equals("SchwarzesBrett"))){
                        //result.equals("Formular")|
                        //result.equals("FreiesFormular"))){
                    db.RemoveDatabase();
                    db = new DatabaseConnector();
                    result = db.connect();
                    DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createUser());
                    DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createRoom());
                    DatabaseConnector.getConnection().createStatement().executeUpdate(TableCfgReader.createBlackboard());
                }

            }
            result = db.connect();
        }
        System.out.println(result);
    }*/

    /**
     * Main Methode zum erstellen des HTTP Servers mit der Configuration der USERService, Raum Serivce, SprechzeitenService und KontaktService
     * HTTP Server wird mit der Internen IP Adresse 192.168.0.101 IP-Adresse des Host rechners. In dem Fall des Raspberry PI's
     * @param args 
     */
    public static void main(String[] args) {
        try {
            //String baseUrl = ( args.length > 0 ) ? args[0] : "http://192.168.0.101:1337";
            // String baseUrl = ( args.length > 0 ) ? args[0] : "https:2//localhost:1337";

            //final HttpServer Server = GrizzlyHttpServerFactory.createHttpServer(
            //	URI.create(baseUrl), new ResourceConfig( TestRestService.class ), false, new SSLEngineConfigurator(new SSLContextConfigurator(), false, false, false) );

            
            Server Rserver = new Server();
            final ResourceConfig config = new ResourceConfig();
            config.registerClasses(UserService.class, RaumService.class, SprechzeitenService.class, KontaktService.class);
            URI url = URI.create("http://192.168.0.101:"+Rserver.getPort());

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(url, config,false );
            Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            } ) );
            System.out.println( String.format( "\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
                            + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
                    url ) );
            server.start();
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if(DatabaseConnector.getConnection() != null){
                try {
                    DatabaseConnector.getConnection().close();
                } catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
            }
        }
    }

    
    public int getPort(){
        return port;
    }
}
