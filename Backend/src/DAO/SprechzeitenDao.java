package DAO;

import DAO.DAOInterfaces.IKontaktDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseUtils.DatabaseConnector;

import Models.Sprechzeiten;
import DAO.DAOInterfaces.ISprechzeitenDao;
import DAO.DAOInterfaces.IUserDao;
import Models.Raum;

public class SprechzeitenDao implements ISprechzeitenDao {

	private DatabaseConnector db;
        IUserDao userDao;
        IKontaktDao kontaktDao;

	/**
	 * Gibt die Sprechzeiten eines Users in einem Raum aus
	 * @param raumNr Raumnummer in der die Sprechzeiten stattfinden
	 * @param benutzerID User der die Sprechzeiten haelt
	 * @return Sprechzeiten eines Users in einem Raum
	 * @throws Exception
	 */
	public Sprechzeiten getSprechzeiten(int raumNr, int benutzerID) throws Exception {
		db = new DatabaseConnector();
		db.connect();
                userDao = new UserDao();
		Sprechzeiten sprechzeiten = new Sprechzeiten();
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			System.out.println(DatabaseConnector.getConnection().toString());


			ResultSet rs = stmt.executeQuery("Select Raumnummer, BenutzerID,  Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Nachricht "
					+ " from Sprechzeiten "
					+ " where Raumnummer = "+ raumNr +" and BenutzerID = "+ benutzerID + "");


			while (rs.next()) {
				sprechzeiten.setRaumNr(rs.getInt("Raumnummer"));
				sprechzeiten.setBenutzerID(userDao.getUserByID(benutzerID));
				sprechzeiten.setMontag(rs.getString("Montag"));
				sprechzeiten.setDienstag(rs.getString("Dienstag"));
				sprechzeiten.setMittwoch(rs.getString("Mittwoch"));
				sprechzeiten.setDonnerstag(rs.getString("Donnerstag"));
				sprechzeiten.setFreitag(rs.getString("Freitag"));
                                sprechzeiten.setNachricht(rs.getString("Nachricht"));

			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(DatabaseConnector.getConnection() != null)
				try {db.close();} catch (SQLException sqle) {
				System.out.println("Fehler bei der Verbindung");
			}
		}
		return sprechzeiten;
	}
        
        /**
         * Funktion zum Updaten der Sprechzeiten
         * @param sprechzeiten
         * @return GIbt an ob es erfolgreich war oder nicht
         * @throws Exception 
         */
        public boolean updateSprechzeiten(Sprechzeiten sprechzeiten) throws Exception {
        int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Update Sprechzeiten set Montag = \"" + sprechzeiten.getMontag() + "\""
                    + ", set Dienstag = \"" + sprechzeiten.getDienstag() + "\""
                    + ", set Mittwoch = \"" + sprechzeiten.getMittwoch() + "\""
                    + ", set Donnerstag = \"" + sprechzeiten.getDonnerstag() + "\""
                    + ", set Freitag = \"" + sprechzeiten.getFreitag() + "\""
                    + ", set Nachricht = \"" + sprechzeiten.getNachricht() + "\""
                    + " where Raumnummer = "+sprechzeiten.getRaumNr() + " and BenutzerID = "+ sprechzeiten.getBenutzerID().getBenutzerID());
            
            
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
            return true;
        else
            return false;
    
        }
        
        /**
         * Funktion zum Updaten der Sprechzeiten für eine neuen Verantwortlichen
         * @param raum
         * @return GIbt an ob es Erfolgreich war oder nicht
         * @throws Exception 
         */
        public boolean updateSprechzeitenByID(Raum raum) throws Exception {
        int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Update Sprechzeiten set BenutzerID = "+ raum.getVerantwortlicher().getBenutzerID() +" where Raumnummer = "+raum.getRaumnummer());
            
            
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
            kontaktDao = new KontaktDao();
            boolean updateKontakt;
            updateKontakt = kontaktDao.updateKontaktID(raum);
            if(updateKontakt == true)
                return true;
            else
                return false;
        }
        else
            return false;
    
        }
}