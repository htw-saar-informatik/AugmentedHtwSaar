package Service;

import DAO.DAOInterfaces.IRaumDao;
import DAO.RaumDao;
import Models.Raum;
import com.google.gson.Gson;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * RestSchnittstellen Klasse für alle Raum bezogene Nachrichten
 */
@Path("raum")
public class RaumService {

    private IRaumDao IRaumDao;

    /**
     * Methode zum Auslesen der Rauminformationen
     * @param raumnummer erwartet die Raumnummer die Aufgerufen wird
     * @return GIbt alle Rauminformationen zu den jeweiligen Raum zurück
     * @throws Exception 
     */
    @GET
    @Path("/rauminfo/{raumnummer}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoom(@PathParam("raumnummer") int raumnummer) throws Exception {
        System.out.println("Start Rauminfo");
        IRaumDao = new RaumDao();
        Gson gson = new Gson();
        String json;

        Raum raum = new Raum();
        raum = IRaumDao.getRaum(raumnummer);
        json = gson.toJson(raum);
        System.out.println("TestRaum Vor versenden");
        return json;
    }
    
    /**
     * Methode zum auslesen von alle vorhandenen Räumen. Wird vom ADMIN auf der Webseite benötigt
     * @return Liefert alle Räume die in der Datenbank vorhanden sind zurück an absender als JSON Object
     * @throws Exception 
     */
    @GET
    @Path("/allrooms")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRoom() throws Exception {
        System.out.println("Starte Alle Räume");
        IRaumDao = new RaumDao();
        Gson gson = new Gson();
        List<Raum> list;
        list = IRaumDao.getAllRooms();
        String json = gson.toJson(list);
        System.out.println("Ende Alle Räumme \n");
        
        return json;
    }
    
    /**
     * Methode zum Auslesen der Räume für den der jweilige Benutzer verantwortlich ist.
     * @param userID Benötigt eine UserID zum Auslesen der jeweiligen Räumen zu dem User
     * @return Gibt alle RÄume zu dem User zurück
     * @throws Exception 
     */
    @GET
    @Path("/allroomsbyuser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRoomByUser(@PathParam("username") int userID) throws Exception {
        System.out.println("Starte Alle Räume by Username");
        IRaumDao = new RaumDao();
        Gson gson = new Gson();
        List<Raum> list;
        list = IRaumDao.getAllRoomsByUser(userID);
        String json = gson.toJson(list);
        System.out.println("Ende Alle Räumme by Username \n");
        
        return json;
    }
    
    /**
     * FUnktion zum Updaten des Raums. Setzt neuen Verantwortlichen, neue HTW-Seite und ändert dementsprechen Sprechzeiten und Kontakt ab.
     * @param data erwartet die neuen Raum informationen als JSON Object
     * @return Gibt an ob es erfolgreich war oder nicht
     * @throws Exception 
     */
    @POST
    @Path("/updateraum")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateRaum(String data) throws Exception {
        System.out.println("Starte Update Raum");
        JsonObjectBuilder json = Json.createObjectBuilder();
        IRaumDao = new RaumDao();
        Gson gson = new Gson();
        Raum raum = gson.fromJson(data, Raum.class);
        boolean updateRaum = IRaumDao.updateRaum(raum);
        if(updateRaum == true)
        {
            System.out.println("Raum erfolgreich geupdatet");
            json.add("Nachricht", "Raum erfolgreich geupdatet");
        }
        else
        {
            System.out.println("Raum nicht erfolgreich geupdatet");
            json.add("Nachricht", "Raum nicht erfolgreich geupdatet");
        }
        return json.build().toString();
    }
}
// Controller im Client ruft GET für die daten auf.