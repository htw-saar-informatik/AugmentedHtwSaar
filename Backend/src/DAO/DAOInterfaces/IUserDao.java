package DAO.DAOInterfaces;

import Models.User;

import java.util.List;

/**
 * Interface zum Kapseln der User-Datenbankzugriffe
 * @author pi
 */
public interface IUserDao {
	public void newUser(User user) throws Exception;
	public String getPassword(String userID) throws Exception;
	public List<User> getUser(String benutzername) throws Exception;
        public User getUserByID(int id) throws Exception;
	public List<User> getUsers() throws Exception;
	public boolean contains(User user) throws Exception;
}
