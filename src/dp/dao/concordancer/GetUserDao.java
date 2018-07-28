package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dp.concordancer.forms.UserForm;
import dp.concordancer.interfaces.GetUserDataAccessObject;

public abstract class GetUserDao extends GetConnection implements GetUserDataAccessObject {
	
	
	public UserForm getUser(String name, String password) {
		UserForm user = new UserForm();
		Connection conn = null;
		String query = "SELECT * FROM users WHERE USER_NAME='" + name + "' AND PASSWORD='" + password + "';";
		try {
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			boolean uexists = result.next();

			if (!uexists) {
				user.setIsRegistered(false);

			} else {

				user.setUserid(result.getInt("USER_ID"));
				user.setUsername(result.getString("USER_NAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setIsRegistered(true);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);

		}

		return user;

	}

	

}
