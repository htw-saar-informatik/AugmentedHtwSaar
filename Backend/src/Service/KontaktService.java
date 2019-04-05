package Service;

import DAO.DAOInterfaces.IKontaktDao;
import DAO.DAOInterfaces.IRaumDao;
import DAO.KontaktDao;
import DAO.RaumDao;
import Models.Kontakt;
import Models.Raum;
import Models.User;
import com.google.gson.Gson;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 *Rest Schnittstelle für alle Kontakt bezogene Funktionen wie neue Nachrichte, auslesen der Nachrichten, Status der Nachricht auf gelesen setzen
 */
@Path("kontakt")
public class KontaktService{
    private IKontaktDao kontaktDao;
    
    /**
     * Methode zum empfangen neuer nachrichten. Wird mit der HTTP-URL /konkakt/neu aufgerufen
     * @param data Daten als Json Object im Body des HTTP aufrufes
     * @return Gibt an, ob es erfolgreich war oder nicht
     * @throws Exception 
     */
    @POST
    @Path("/neu")
    @Consumes("application/json")
    public String neueNachricht(String data) throws Exception {
        System.out.println("Neue Nachricht");
        kontaktDao = new KontaktDao();
        JsonObjectBuilder json = Json.createObjectBuilder();
        Gson gson = new Gson();
        Kontakt kontakt = gson.fromJson(data, Kontakt.class);
        boolean nachrichtGespeichert;
        nachrichtGespeichert = kontaktDao.neueNachricht(kontakt);
       
        if(nachrichtGespeichert == true)
        {
            System.out.println("Nachricht erfolgreich gespeichert\n");
            json.add("Nachricht", "Nachricht wurde hinterlegt");
        }
        else
        {
            System.out.println("Nachricht konnte nicht gespeichert werden\n");
            json.add("Nachricht", "Nachricht konnte nicht gespeichert werden");
        }
        return json.build().toString();

    }
    
    /**
     * Methode zum ändern des Gelesen Statuses
     * @param data Daten als Json Object im Body des HTTP aufrufes
     * @return Gibt an, ob es erfolgreich war oder nicht
     * @throws Exception 
     */
    @POST
    @Path("/gelesen")
    @Consumes("application/json")
    public String nachrichtGelesen(String data) throws Exception {
        System.out.println("Nachricht gelesen");
        kontaktDao = new KontaktDao();
        JsonObjectBuilder json = Json.createObjectBuilder();
        Gson gson = new Gson();
        Kontakt kontakt = gson.fromJson(data, Kontakt.class);
        
        boolean nachrichtGelesen;
        nachrichtGelesen = kontaktDao.nachrichtGelesen(kontakt);
        if(nachrichtGelesen == true)
        {
            System.out.println("Nachricht gelesen erfolgreich\n");
            json.add("Nachricht", "Nachricht erfolgreich gelesen");
        }
        else
        {
            System.out.println("Nachricht konnte gelesen werden\n");
            json.add("Nachricht", "Nachricht konnte nicht gelesen werden");
        }
        
        return json.build().toString();
    }
    
    /**
     * Methode zum Auslesen aller Nachrichten für den Jeweiligen Raum und den Empfänger des Raums
     * @param raumnummer erwartet die raumnummer und empfaengerID
     * @return Liefert alle Nachrichten zu dem jeweiligen Raum zurück
     * @throws Exception 
     */
    @GET
    @Path("/allenachrichten/{raumnummer}/{empfaengerID}")
    @Consumes("application/json")
    public String alleNachrichten(@PathParam("raumnummer") int raumnummer, @PathParam("empfaengerID") int empfaengerID) throws Exception{
        System.out.println("Starte abruf alle Nachrichten");
        kontaktDao = new KontaktDao();
        Gson gson = new Gson();
        String json;
        List<Kontakt> listKontakt;
        
        listKontakt = kontaktDao.alleNachrichten(raumnummer, empfaengerID);
        
        json = gson.toJson(listKontakt);
        return json;
        
    }
    
}
