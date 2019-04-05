/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.DAOInterfaces;

import Models.Kontakt;
import Models.Raum;
import java.util.List;


/**
 * Interface Klasse zur Kapselung der Kontakt spezifischen Datenbankugriffe
 * @author pi
 */
public interface IKontaktDao {
    public boolean neueNachricht(Kontakt kontakt) throws Exception;
    public boolean nachrichtGelesen(Kontakt kontakt) throws Exception;
    public boolean updateKontaktID(Raum raum) throws Exception;
    public List<Kontakt> alleNachrichten(int raumnummer, int empfaengerID) throws Exception;
}
