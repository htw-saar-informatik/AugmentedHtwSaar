package DAO.DAOInterfaces;

import Models.Raum;
import java.util.List;

/**
 * Interface Klasse zur Kapselung der Raum spezifischen Datenbankzugriffe
 * @author pi
 */
public interface IRaumDao {

	public Raum getRaum(int raumNr) throws Exception;
        public boolean updateRaum(Raum raum) throws Exception;
        public List<Raum> getAllRooms() throws Exception;
        public List<Raum> getAllRoomsByUser(int userID) throws Exception;
}
