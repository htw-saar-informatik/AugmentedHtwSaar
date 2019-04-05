package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DAOInterfaces.IUserDao;
import DatabaseUtils.DatabaseConnector;
import Models.User;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Class for "User" objects in the database
 *
 */
public class UserDao implements IUserDao {

	private DatabaseConnector db;

    /**
     * Gibt das User-Objekt eines Users in der Datenbank zurueck
     * @param benutzerID NuterId des Users
     * @return Gibt das User-Objekt zurueck
     * @throws Exception
     */
	public List<User> getUser(String benutzername)throws Exception{
		db = new DatabaseConnector();
		db.connect();
		User user = null;
                List<User> userListe = new ArrayList<User>();
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());


			ResultSet rs = stmt.executeQuery("SELECT BenutzerID, Benutzername, Name, Vorname, Passwort, EMail, User.Rolle as RollenID, Bezeichnung "
                                + "FROM USER join StammtabelleRolle on User.Rolle = StammtabelleRolle.RollenID "
                                + "where Benutzername = lower(\"" + benutzername+"\")");
			        

                        while (rs.next()) {
                            user = new User();
                            user.setBenutzerID(rs.getInt("BenutzerID"));
                            user.setBenutzername(rs.getString("Benutzername"));
                            user.setName(rs.getString("Name"));
                            user.setVorname(rs.getString("Vorname"));
                            user.setRollenID(rs.getInt("RollenID"));
                            user.setRollenbezeichnung(rs.getString("Bezeichnung"));
                            user.setEMail(rs.getString("EMail"));
                            user.setPasswort(rs.getString("Passwort"));
                            userListe.add(user);
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null){
			    try {
                db.close();
                } catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
			}
		}
		return userListe;
	}
        
        /**
         * Gibt alle User Informationen zu einer bestimmen ID zurück
         * @param id
         * @return
         * @throws Exception 
         */
        public User getUserByID(int id) throws Exception
        {
                db = new DatabaseConnector();
		db.connect();
		User user = null;
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());


			ResultSet rs = stmt.executeQuery("SELECT BenutzerID, Benutzername, Name, Vorname,  EMail, User.Rolle as RollenID, Bezeichnung "
                                + " FROM USER join StammtabelleRolle on User.Rolle = StammtabelleRolle.RollenID "
                                + " where BenutzerID ="+id);
			        

                    while (rs.next()) {
                        user = new User();
                        user.setBenutzerID(rs.getInt("BenutzerID"));
                        user.setBenutzername(rs.getString("Benutzername"));
                        user.setName(rs.getString("Name"));
                        user.setVorname(rs.getString("Vorname"));
                        user.setRollenID(rs.getInt("RollenID"));
                        user.setRollenbezeichnung(rs.getString("Bezeichnung"));
                        user.setEMail(rs.getString("EMail"));
                       
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null){
			    try {
                db.close();
                } catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
			}
		}
		return user;
        }

    /**
     * Legt einen Benutzer anhand eines User-Objekts in der Datenbank an
     * @param user Ist zu speichernder user
     * @throws Exception
     */
	public void newUser(User user) throws Exception {
		db = new DatabaseConnector();
		db.connect();
  		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());
            int rollenid = 0;
            switch(user.getRollenBezeichnung().trim()){
            	case "Admin": user.setRollenID(0);
            	break;
            	case "Professor" : user.setRollenID(1);
            	break;
            	case "Student" : user.setRollenID(2);
            	break;
            	default:
            		user.setRollenID(3);
            }

			stmt.executeUpdate("Insert Into USER (Benutzername, Passwort, Name, Vorname, Rolle, EMail) Values("
                                + "lower(\"" +user.getBenutzername().trim() + "\")"
                                + ",\"" + user.getPasswort().trim() + "\""
                                + ",\"" + user.getName().trim() + "\""
                                + ",\"" + user.getVorname().trim() +"\""
                                + "," + user.getRollenID()
                                + ",\"" + user.getEMail().trim() +"\" )");
                        
		} catch(SQLException e)	{
  			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null)
				try {db.close();
			}catch (SQLException sqle) {
				System.out.println("Fehler bei der Verbindung");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prüft ob in der Datenbank bereits ein User vorhanden ist
	 * @param user User-Objekt des zu suchenden Users
	 * @return Ergebniss der Pruefung
	 */
	public boolean contains(User user) throws Exception {
			db = new DatabaseConnector();
			db.connect();
			int count = 0;
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());

			ResultSet rs = stmt.executeQuery("Select Count(*) from User "
                                + "where Benutzername = lower(\"" + user.getBenutzername()+"\")");
                        while(rs.next()){
                            count = rs.getInt("COUNT(*)");
                        }
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null)
				try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(count != 0){
			return true;
		}
		return false;
	}

	/**
	 * Gibt das Passwort eines Benutzers in der Datenbank  zurück
	 * @param benutzerID Identifier des Benutzers
	 * @return Das Passwort eines Benutzers
	 */
	public String getPassword(String benutzerID) throws Exception {
		db = new DatabaseConnector();
		db.connect();
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());

			ResultSet rs = stmt.executeQuery("Select password from USER where BenutzerID = "+benutzerID);
			return rs.getString("Password");
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null)
				try {
					db.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	/**
	 * Gibt alle Benutzer die in der Datenbank entahlten sind zurueck
	 * @return Liste an User-Objekten
	 */
	public List<User> getUsers() throws Exception {
		db = new DatabaseConnector();
		db.connect();
		List<User> users = null;
		User user;
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());

			ResultSet rs = stmt.executeQuery("Select BenutzerID, Benutzername, Name, Vorname, Rolle from USER where Rolle < 2");
			users = new ArrayList<User>();
			while(rs.next()){
                            int benutzerID = rs.getInt("BenutzerID");
                            String benutzername = rs.getString("Benutzername");
                            String name = rs.getString("Name");
                            String vorname = rs.getString("Vorname");
                            
                            user = new User(benutzerID, benutzername, name, vorname);
                            user.setRollenID(rs.getInt("Rolle"));
                                
                            users.add(user);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(DatabaseConnector.getConnection() != null)
				try {
					db.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return  users;
	}
}