package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * class GetConnection that implements methods for accessing the MySQL 8.0 Database
 * using the JDBC driver.
 */

public class GetConnection {
	

		static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
			}
		}
/*
 * Method getConnection to get a connection to the DB.
 */
		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/concordances?autoReconnect=true&useSSL=FALSE&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		}
/*
 * Method closeConnection to close the connection to the DB.
 */
		public void closeConnection(Connection connection) {
			if (connection == null)
				return;
			try {
				connection.close();
			} catch (SQLException ex) {
			}
		}

	
	
	
	
	
}
