package Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DAO.SprechzeitenDao;
import Models.Sprechzeiten;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import DAO.DAOInterfaces.ISprechzeitenDao;
import com.google.gson.Gson;

/**
 * Restschnittstellen Klasse für alle Sprechzeiten bezogene Methoden
 * @author pi
 */
@Path("sprechzeiten")
public class SprechzeitenService {

	private ISprechzeitenDao sprechzeitenDao;
	
	
        /**
         * Funktion zum auselsen der Sprechzeiten zu einem gewünschten Raum
         * @param raumnummer
         * @param benutzerID
         * @return Gibt die Sprechzeiten zu einem Raum und Benutzer als JSON Object zurück
         * @throws Exception 
         */
	@GET
   	@Path("raumnummer/{raumnummer}/benutzerID/{benutzerID}")
   	@Produces( MediaType.APPLICATION_JSON )
   	public String LadeSprechzeiten(@PathParam("raumnummer") int raumnummer, @PathParam("benutzerID") int benutzerID) throws Exception {
                System.out.println("Lade Sprechzeiten");
		sprechzeitenDao = new SprechzeitenDao();
		
   		String json;
                Gson gson = new Gson();
 
   		Sprechzeiten sprechzeiten = new Sprechzeiten();
   		sprechzeiten = sprechzeitenDao.getSprechzeiten(raumnummer, benutzerID);
                json = gson.toJson(sprechzeiten);
   		
   		
                System.out.println("Sprechzeiten gesendet\n");
		return json;
   	}
        
        /**
         * Methode zum updaten der Sprechzeiten eines Raums.
         * @param data Erwartet die neuen Sprechzeiten als JSON Object
         * @return Gibt an ob es erfolgreich war oder nicht
         * @throws Exception 
         */
        @POST
        @Path("/updatesprechzeiten")
        @Produces(MediaType.APPLICATION_JSON)
        public String updateSprechzeiten(String data) throws Exception
        {
            System.out.println("Starte Update Sprechzeiten");
            sprechzeitenDao = new SprechzeitenDao();
            JsonObjectBuilder json = Json.createObjectBuilder();
            Gson gson = new Gson();
            Sprechzeiten sprechzeiten = gson.fromJson(data, Sprechzeiten.class);
            boolean sprechzeitenUpdate = sprechzeitenDao.updateSprechzeiten(sprechzeiten);
            if(sprechzeitenUpdate == true)
            {
                System.out.println("Sprechzeiten erfolgreich geupdatet");
                json.add("Nachricht", "Sprechzeiten erfolgreich geupdatet");
            }
            else
            {
                System.out.println("Sprechzeiten update fehlgeschlagen\n");
                json.add("Nachricht", "Sprechzeiten update fehlgeschlagen");
            }
            return json.build().toString();
        }
}
