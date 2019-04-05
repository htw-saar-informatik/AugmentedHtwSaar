/*package Models.UsersNotInUse;

import DatabaseUtils.DatabaseConnector;
import Models.User;
import DAO.DAOInterfaces.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;
import java.util.List;

@Path("User")
public class AccountService {

    private IUserDao userDao;

    @POST
    @Path("/Login")
    @Consumes( MediaType.APPLICATION_JSON )
    public String Login(String loginModel){
        JsonReader jsonReader = Json.createReader(new StringReader(loginModel));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        object.getInt("UserId");
        object.getString("Benutzername");
        object.getString("Name");
        object.getString("Vorname");
        object.getString("Passwort");
        object.getString("EMail");
        object.getString("Fachbereich");
        object.getString("Rolle");
        return null;
    }

    @POST
    @Path("/Register")
    @Consumes( MediaType.APPLICATION_JSON )
    public String Register(String registerModel) throws Exception {
        JsonReader jsonReader = Json.createReader(new StringReader(registerModel));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        User modelUser = new User();
        modelUser.setBenutzerID(object.getInt("UserId"));
        modelUser.setBenutzername(object.getString("Benutzername"));
        modelUser.setName(object.getString("Name"));
        modelUser.setVorname(object.getString("Vorname"));
        modelUser.setPasswort(object.getString("Passwort"));
        modelUser.setEmail(object.getString("EMail"));
        modelUser.setFachbereich(object.getString("Fachbereich"));

        List<User> users = userDao.getUsers();
        for (User user : users ) {
            if(user.getBenutzername().equals(modelUser.getBenutzername()) && user.getPasswort().equals(modelUser.getPasswort())){
                return "Schon vorhanden";
            }
        }
        userDao.newUser(modelUser);
        return "Erfolg";
    }

/*
    @GET
    @Path("/ForgotPassword")
    @Produces( MediaType.APPLICATION_JSON )
    public String ForgotPassword(){

    }

    @POST
    @Path("/ForgotPassword")
    @Consumes( MediaType.APPLICATION_JSON )
    public String ForgotPassword(ForgotPasswordViewModel viewModel){

    }


*/

