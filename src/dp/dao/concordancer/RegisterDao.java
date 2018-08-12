package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dp.concordancer.interfaces.*;

/*class RegisterDao for registering new users
 * with the database.
 * 
 */

public class RegisterDao extends GetUserDao implements RegisterDataAccessObject {

/*
 * (non-Javadoc)
 * @see dp.concordancer.interfaces.RegisterDataAccessObject#checkUserName(java.lang.String)
 */
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
/*
 * (non-Javadoc)
 * @see dp.concordancer.interfaces.RegisterDataAccessObject#registerUser(java.lang.String, java.lang.String)
 */
	public void registerUser(String name, String password) {
		Connection conn = null;

		try {
			String sql = "INSERT INTO users (USER_NAME, PASSWORD) VALUES (?, ?)";
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);
		}
	}

}
