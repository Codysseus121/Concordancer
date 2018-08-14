package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dp.concordancer.interfaces.GetUserDataAccessObject;
import dp.model.concordancer.User;


/*A DAO class for user login that for getting
 * use data from the database.
 * 
 */


public class GetUserDao extends GetConnection implements GetUserDataAccessObject {

	/*
	 * Method getUser() to get User details from the persistence layer and return a UserForm object.
	 * The UserForm object will be used by the servlet to create the User object for the session.
	 * @param String name: the username
	 * @param String password: the password
	 */
	
	public  User getUser(String name, String password) {
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

	

}
