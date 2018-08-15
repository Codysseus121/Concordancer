package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dp.concordancer.interfaces.UserDataAccessObject;
import dp.model.concordancer.User;

public class UserDao extends GetConnection implements UserDataAccessObject {

	/*
	 * Method getUser() to get User details from the persistence layer and return a
	 * UserForm object. The UserForm object will be used by the servlet to create
	 * the User object for the session.
	 * 
	 * @param String name: the username
	 * 
	 * @param String password: the password
	 */

	public User getUser(String name, String password) {
		User user = new User();
		Connection conn = null;
		String query = "SELECT * FROM users WHERE USER_NAME='" + name + "' AND PASSWORD='" + password + "';";
		try {
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			boolean uexists = result.next();

			if (!uexists) {
				user.setIsRegistered(false);
				return null;

			} else {

				user.setUserid(result.getInt("USER_ID"));
				user.setUsername(result.getString("USER_NAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setIsRegistered(true);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

		finally {
			closeConnection(conn);

		}

		return user;

	}

	public boolean checkUserName(String username) {

		boolean available = false;
		Connection conn = null;
		String query = "SELECT * FROM users WHERE USER_NAME='" + username + "';";
		try {
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			boolean uexists = result.next();

			if (!uexists) {
				available = true;
			} else {

				available = false;
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);

		}

		return available;
	}

	public boolean registerUser(String name, String password) {

		Connection conn = null;

		try {
			String sql = "INSERT INTO users (USER_NAME, PASSWORD) VALUES (?, ?)";
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.execute();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;

		}

		finally {

			closeConnection(conn);
		}
	}

}
