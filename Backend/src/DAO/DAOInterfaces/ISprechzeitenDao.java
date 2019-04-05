package DAO.DAOInterfaces;

import Models.Raum;
import Models.Sprechzeiten;

/**
 * Interface Klasse zur Kapselung der Sprechzeiten bezogenen Datenbankzugriffe
 * @author pi
 */
public interface ISprechzeitenDao {
	public Sprechzeiten getSprechzeiten(int raumNr, int benutzerID) throws Exception;
        public boolean updateSprechzeiten(Sprechzeiten sprechzeiten) throws Exception;
        public boolean updateSprechzeitenByID(Raum raum) throws Exception;

}
