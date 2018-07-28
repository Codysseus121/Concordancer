package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	

		static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
			}
		}

		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/concordances?autoReconnect=true&useSSL=FALSE&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		}

		public void closeConnection(Connection connection) {
			if (connection == null)
				return;
			try {
				connection.close();
			} catch (SQLException ex) {
			}
		}

	
	
	
	
	
}
