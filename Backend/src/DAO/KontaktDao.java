/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.DAOInterfaces.IKontaktDao;
import DAO.DAOInterfaces.IUserDao;
import DatabaseUtils.DatabaseConnector;
import Models.Kontakt;
import Models.Raum;
import Models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pi
 */
public class KontaktDao implements IKontaktDao{
    
    DatabaseConnector db;
    IUserDao userDao = new UserDao(); 

    /**
     * Funktion zum anlegen einer neuen Kontakt nachricht
     * @param kontakt
     * @return GIbt an ob es erfolgreich war oder nicht
     * @throws Exception 
     */
    @Override
    public boolean neueNachricht(Kontakt kontakt) throws Exception{
        int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Insert Into Kontakt (Raumnummer, SenderID, EmpfaengerID, Prio, Betreff, Nachricht, Gelesen)"
                                + " Values ("
                                + ""+kontakt.getRaumnummer() 
                                + ", "+kontakt.getsender().getBenutzerID()
                                + ", " + kontakt.getEmpfaenger().getBenutzerID()
                                + ", " + kontakt.getPrio() 
                                + ", \"" + kontakt.getBetreff() + "\""
                                + ", \"" + kontakt.getNachricht() + "\""
                                + ", 0)");
            
            
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
     * Funktuin zum ändern des Gelesen Statuses
     * @param kontakt
     * @return Gibt an ob die Nachricht erfolgreich geupdatet wurde oder nicht
     * @throws Exception 
     */
     public boolean nachrichtGelesen(Kontakt kontakt) throws Exception{
        int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Update Kontakt set Gelesen = 1 where NachrichtenID = "+ kontakt.getNachrichtenID() + "");
            
            
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
      * Funktion zum setzen einer neuen EmpfängerID bei den Nachrichten
      * @param raum
      * @return
      * @throws Exception 
      */
     public boolean updateKontaktID(Raum raum) throws Exception{
        int anzahl = 0;
        db = new DatabaseConnector();
        db.connect();
        
        try {
            Statement stmt = db.getConnection().createStatement();
            
            
            anzahl = stmt.executeUpdate("Update Kontakt set EmpfaengerID = "+raum.getVerantwortlicher().getBenutzerID() + " where Raumnummer = "+ raum.getRaumnummer());
            
            
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if(db.getConnection() != null)
                try{db.close();
                }catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
        }
        
        if(anzahl >= 1)
            return true;
        else
            return false;
    }
     
     /**
      * Methode zum Abrufen alle Nachrichten zu einem Bestimmten Raum und empfänger
      * @param raumnummer
      * @param empfaengerID
      * @return
      * @throws Exception 
      */
    public List<Kontakt> alleNachrichten(int raumnummer, int empfaengerID) throws Exception {
        db = new DatabaseConnector();
		db.connect();
                List<Kontakt> alleNachrichten = new ArrayList<Kontakt>();
                userDao = new UserDao();
                
                 
        try {
            Statement stmt = db.getConnection().createStatement();
            
            ResultSet rs = stmt.executeQuery("Select * from Kontakt where Gelesen = 0 and Raumnummer = "+ raumnummer+ " and EmpfaengerID = " + empfaengerID + "");
                               
			
			
			while (rs.next()) {
                          Kontakt kontakt = new Kontakt();
                          kontakt.setNachrichtenID(rs.getInt("NachrichtenID"));
                          kontakt.setRaumnummer(rs.getInt("Raumnummer"));
                          kontakt.setSender(userDao.getUserByID(rs.getInt("SenderID")));
                          kontakt.setPrio(rs.getInt("Prio"));
                          kontakt.setBetreff(rs.getString("Betreff"));
                          kontakt.setNachricht(rs.getString("Nachricht"));                         
                          
                          alleNachrichten.add(kontakt);
                        
                        }
            
   
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if(db.getConnection() != null)
                try{db.close();
                }catch (SQLException sqle) {
                    System.out.println("Fehler bei der Verbindung");
                }
        }
                return alleNachrichten;
    }
    
    
    
}
