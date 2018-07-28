package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dp.concordancer.forms.UserForm;

public class LoginDao implements LoginDataAccessObject {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/concordances?autoReconnect=true&useSSL=FALSE&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "");
	}

	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException ex) {
		}
	}

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
