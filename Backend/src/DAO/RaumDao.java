package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.DAOInterfaces.*;
import DAO.DAOInterfaces.IUserDao;
import DatabaseUtils.DatabaseConnector;
import Models.Raum;
import Models.User;
import java.util.*;

public class RaumDao implements IRaumDao {

	DatabaseConnector db;
        IUserDao iUserDao;
        ISprechzeitenDao iSprechzeitenDao;

        /**
         * FUnktion zum Auslesen der Rauminformationen zu einer bestimmten RaumNr
         * @param raumNr
         * @return
         * @throws Exception 
         */
	public Raum getRaum(int raumNr) throws Exception {
                iUserDao = new UserDao();
		db = new DatabaseConnector();
		db.connect();
		Raum raum = new Raum();
		try {
			Statement stmt = db.getConnection().createStatement();
			System.out.println(db.getConnection().toString());
			ResultSet rs = stmt.executeQuery("Select Raumnummer, Verantwortlicher, Raum.Raumart as RaumartID, StammtabelleRaumart.Bezeichnung as Bezeichnung, HTWSeite "
                                + " from Raum join StammtabelleRaumart on Raum.Raumart = StammtabelleRaumart.RaumartID"
                                + " where Raumnummer = "+ raumNr);
			
			
			while (rs.next()) {
                                raum.setRaumnummer(rs.getInt("Raumnummer"));
                                raum.setVerantwortlicher(iUserDao.getUserByID(rs.getInt("Verantwortlicher")));
                                raum.setRaumartID(rs.getInt("RaumartID"));
                                raum.setRaumartBezeichnung(rs.getString("Bezeichnung"));
                                raum.setHtwSeite(rs.getString("HTWSeite"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(db.getConnection() != null)
				try {db.close();
			} catch (SQLException sqle) {
				System.out.println("Fehler bei der Verbindung");
			}
		}
                return raum;

        }
        /**
         * Update eines Raums. Setzt einen neuen Verantwortlichen in einem Raum
         * @param raum
         * @return
         * @throws Exception 
         */
        public boolean updateRaum(Raum raum) throws Exception{
            int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Update Raum set Verantwortlicher = "+raum.getVerantwortlicher().getBenutzerID() +",  HTWSeite = \""+raum.getHtwSeite() + "\" where Raumnummer = " + raum.getRaumnummer());
            
            
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if(db.getConnection() != null)
                try{db.close();
                }catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
        }
        
        if(anzahl == 1)
        {
            iSprechzeitenDao = new SprechzeitenDao();
            boolean updateSprechzeiten = iSprechzeitenDao.updateSprechzeitenByID(raum);
            if(updateSprechzeiten == true)
                return true;
            else
                return false;
        }
        else
            return false;
    
        
        }
        
        /**
         * Funktion zum auslesen von allen Räumen die auf der Datenbank gespeichert sind
         * @return Rückgabe von allen Räumen in einer Liste
         * @throws Exception 
         */
        public List<Raum> getAllRooms() throws Exception
        {
                iUserDao = new UserDao();
		db = new DatabaseConnector();
		db.connect();
                List<Raum> allRooms = new ArrayList<Raum>();
		
		try {
			Statement stmt = db.getConnection().createStatement();
			System.out.println(db.getConnection().toString());
			ResultSet rs = stmt.executeQuery("Select Raumnummer, Verantwortlicher, Raum.Raumart as RaumartID, StammtabelleRaumart.Bezeichnung as Bezeichnung, HTWSeite "
                                + " from Raum join StammtabelleRaumart on Raum.Raumart = StammtabelleRaumart.RaumartID");
                               
			
			
			while (rs.next()) {
                                Raum raum = new Raum();
                                raum.setRaumnummer(rs.getInt("Raumnummer"));
                                raum.setVerantwortlicher(iUserDao.getUserByID(rs.getInt("Verantwortlicher")));
                                raum.setRaumartID(rs.getInt("RaumartID"));
                                raum.setRaumartBezeichnung(rs.getString("Bezeichnung"));
                                raum.setHtwSeite(rs.getString("HTWSeite"));
                                allRooms.add(raum);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(db.getConnection() != null)
				try {db.close();
			} catch (SQLException sqle) {
				System.out.println("Fehler bei der Verbindung");
			}
		}
                return allRooms;
        }
        
        /**
         * Auslesen von allen Räumen bei der ein Benutzer als Verantwortlichger gesetzt ist
         * @param userID
         * @return Liste mit allen Räumen zu dem jeweiligen Benutzer
         * @throws Exception 
         */
        public List<Raum> getAllRoomsByUser(int userID) throws Exception
        {
                iUserDao = new UserDao();
		db = new DatabaseConnector();
		db.connect();
                List<Raum> allRooms = new ArrayList<Raum>();
		
		try {
			Statement stmt = db.getConnection().createStatement();
			System.out.println(db.getConnection().toString());
			ResultSet rs = stmt.executeQuery("Select Raumnummer, Verantwortlicher, Raum.Raumart as RaumartID, StammtabelleRaumart.Bezeichnung as Bezeichnung, HTWSeite "
                                + " from Raum join StammtabelleRaumart on Raum.Raumart = StammtabelleRaumart.RaumartID "
                                + " where Verantwortlicher = "+ userID +"" );
                               
			
			
			while (rs.next()) {
                                Raum raum = new Raum();
                                raum.setRaumnummer(rs.getInt("Raumnummer"));
                                raum.setVerantwortlicher(iUserDao.getUserByID(rs.getInt("Verantwortlicher")));
                                raum.setRaumartID(rs.getInt("RaumartID"));
                                raum.setRaumartBezeichnung(rs.getString("Bezeichnung"));
                                raum.setHtwSeite(rs.getString("HTWSeite"));
                                allRooms.add(raum);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			if(db.getConnection() != null)
				try {db.close();
			} catch (SQLException sqle) {
				System.out.println("Fehler bei der Verbindung");
			}
		}
                return allRooms;
        }
}