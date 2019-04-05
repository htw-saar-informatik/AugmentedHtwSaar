package Service;

import DAO.RaumDao;
import Models.User;
import DAO.UserDao;
import Models.Raum;
import com.google.gson.Gson;
import org.jvnet.hk2.annotations.Service;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Marco Becker, Alexander Huber
 * @version 16.08.2018
 */
@Path("user")
public class UserService {
    
    UserDao userDao = new UserDao();

    /**
     * Rest Methode für den Login, erwartet als Parameter benutzername und das passwort. Überprüfen der Anmelde daten mit den Daten aus der Datenbank
     * Bei erfolgreichem Login rückgabe der Benutzerinformationen via JSON Object
     * @param benutzername
     * @param passwort
     * @return Anmeldedaten als JSON
     * @throws Exception 
     */
    @GET
    @Path("/login/benutzername/{benutzername}/passwort/{passwort}")
    @Produces( MediaType.APPLICATION_JSON )
    public String login(@PathParam("benutzername") String benutzername, @PathParam ("passwort") String passwort) throws Exception {

        System.out.println("Anmeldungsservice");
        System.out.println("Start " + System.currentTimeMillis());
        

        User user = userAuthentification(benutzername, passwort);
        Gson gson = new Gson();
        String json;
        if(user.getBenutzerID() != 0){
            json = gson.toJson(user);
        }
        else{
            json = gson.toJson(user);
            
        }
        System.out.println("Ende " + System.currentTimeMillis());
        return json;
    }


    /**
     * Loginmethode via REST POST Schnittstelle, erwartet die Daten zum Anmelden im Body der HTTP anfrage. Benutzername und Passwort als JSON Object.
     * Konvertiert JSON Object in USER Classe und führt User authentifizierung durch.
     * Bei erfolgreichem übereinstimmen der Passwörter rückgabe der Daten als JSON
     * @param loginModel
     * @return 
     * @throws Exception 
     */
    @POST
    @Path("/login")
    @Consumes( MediaType.APPLICATION_JSON )
    public String Login(String loginModel) throws Exception {
        Gson gson = new Gson();
        User user = gson.fromJson(loginModel, User.class);
        user = userAuthentification(user.getBenutzername(), user.getPasswort());
        
        System.out.println("Anmeldungsservice");
        System.out.println("Start " + System.currentTimeMillis());
        JsonObjectBuilder json = Json.createObjectBuilder();

        

        if(user.getBenutzerID() != 0){
            json.add("BenutzerID", user.getBenutzerID())
            .add("Benutzername", user.getBenutzername())
            .add("Name", user.getName())
            .add("Vorname", user.getVorname())
            .add("RollenID", user.getRollenID())
            .add("Rollenbezeichnung", user.getRollenBezeichnung())
            .add("EMail", user.getEMail())
            .add("Passwort", user.getPasswort());
            
        }
        else{   
            json.add("Benutzername", user.getBenutzername())
            .add("Passwort", user.getPasswort());
        }
        System.out.println("Ende " + System.currentTimeMillis());
        return json.build().toString();
        }
    

    /**
     * Methode zum Registrieren eines neuen Benutzers. Erwartet die Variablen für Registrierung im Body
     * @param data
     * @return ob Registrierung erfolgreich war oder nicht.
     * @throws Exception 
     */
    @POST
    @Path("/register")
    @Consumes("application/json")
    public String register(String data) throws Exception {
        JsonObjectBuilder json = Json.createObjectBuilder();
        Gson gson = new Gson();
        User user = gson.fromJson(data, User.class);
        if (!userDao.contains(user)){
            System.out.println("User noch nicht vorhanden, wird angelegt.");
            userDao.newUser(user);
            System.out.println("User angelegt");
            json.add("Registrierung","Registrierung erfolgreich");
        }else{
            System.out.println("User bereits vorhanden");
            json.add("Registrierung", "Registrierung fehlgeschlagen, User vorhanden");
        }
        return json.build().toString();

    }
    
    /**
     * Gibt alle Users in der Datenbank zurück. Wird benötigt für neuen Verantwortlichen auf Raum zu zuweisen.
     * @return alle Users als JSON Object
     * @throws Exception 
     */
    @GET
    @Path("/allusers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() throws Exception{
        
        System.out.println("Starte Alle Users");
        Gson gson = new Gson();
        List<User> list;
        list = userDao.getUsers();
        String json = gson.toJson(list);
        System.out.println("Ende Alle Users\n");
        
        return json;
        
    }

 /*
    
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public String Get() throws Exception {
        userDao = new UserDao();
        List<User> users = userDao.getUsers();
        JsonArrayBuilder jsonList = Json.createArrayBuilder();
        for (User user : users) {
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("BenutzerID",user.getBenutzerID())
                    //.add("Benutzername", user.getBenutzername())
                    .add("Name", user.getName())
                    .add("Vorname", user.getVorname())
                    .add("Passwort", user.getPasswort())
                    .add("EMail", user.getEmail())
                    .add("RollenID", user.getRollenID())
                    .add("Rollenbezeichnung",user.getRollenbezeichnung());
                    //.add("Angemeldet", user.getAnmeldung())
                    //.add("Meldung", user.getMeldung());
                    //.add("Fachbereich", user.getFachbereich())
                    //.add("Rolle", user.getRolle());
            jsonList.add(json);
        }
        JsonObject json = Json.createObjectBuilder().add("Users", jsonList).build();
        return json.toString();
    }


    @GET
    @Path("/{benutzerID}")
    @Produces( MediaType.APPLICATION_JSON )
    public String Get(@PathParam("benutzerID") int userId) throws Exception {
        userDao = new UserDao();
        User user = userDao.getUser(userId);
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("BenutzerID",user.getBenutzerID())
                //.add("Benutzername", user.getBenutzername())
                .add("Name", user.getName())
                .add("Vorname", user.getVorname())
                .add("Passwort", user.getPasswort())
                .add("EMail", user.getEmail())
                .add("RollenID", user.getRollenID())
                .add("Rollenbezeichnung",user.getRollenbezeichnung());
                //.add("Angemeldet", user.getAnmeldung())
                //.add("Meldung", user.getMeldung());
        //.add("Fachbereich", user.getFachbereich())
        //.add("Rolle", user.getRolle());
        return json.build().toString();

    }

   */
    /**
     * Methode um UserAuthentifizierung durchzuführen, erwartet benutzername und passwort.
     * Vergleich des Benutzers der sich angemeldet hat mit dem User der in der Datenbank steht.
     * @param benutzername
     * @param passwort
     * @return
     * @throws Exception 
     */
public User userAuthentification(String benutzername, String passwort) throws Exception {
        User anmeldeUser = new User(benutzername.toLowerCase(), passwort);

        List<User> datenbankUser = userDao.getUser(benutzername.toLowerCase());
        
        for(User user : datenbankUser)
        {
            if(anmeldeUser.getPasswort().equals(user.getPasswort()) )
                return user;
        }
        anmeldeUser.setBenutzerID(0);
        
            return anmeldeUser;   

    }
}
