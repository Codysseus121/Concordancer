package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcordanceDao extends GetConnection {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		ConcordanceDao cdao = new ConcordanceDao();
		List<String> concs = new ArrayList<String>();
		concs = cdao.getConc("angel");
//		for (String s : concs)
//		{System.out.println(s);}
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
	}

	public List<String>  getConc(String kwic) {
		Connection conn = null;
		ResultSet result = null;
		List<String> concs = new ArrayList<String>();

		try {
			conn = getConnection();
			int count = 1;
			
			boolean more = true;
			String sql = "select REGEXP_SUBSTR (file_content,  '.{0,50}" + kwic
					+ ".{0,50}',1, (?)) from files where file_id=8;";
			PreparedStatement statement = conn.prepareStatement(sql);
			// result = statement.executeQuery();
			while (more) {

				statement.setInt(1, count);
				result = statement.executeQuery();
				result.next();
				if (result.getString(1)==null)
				{more=false;}
				concs.add((result.getString(1)));
				count++;
			
			}
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);

		}
		return concs;
	}

}
