package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcordanceDao extends GetConnection {
	
	public static void main (String [] args)
	{
		ConcordanceDao cdao = new ConcordanceDao();
		cdao.getConc("bright");
	}

	public void getConc (String kwic)
	{
		Connection conn = null;
		ResultSet result = null;
		
		try
		{
			conn = getConnection();
			int count=1;
			boolean more = true;
			String sql = "select REGEXP_SUBSTR (file_content,  '.{0,50}" + kwic + ".{0,50}',1, (?)) from files where file_id=8;";
			PreparedStatement statement = conn.prepareStatement(sql);
			//result = statement.executeQuery();
			while (more)
			{
				
				statement.setInt(1, count);
				result = statement.executeQuery();
				result.next();
				System.out.println(result.getString(1));				
				count++;
				
				if (result.getString(1)==null)
				{more=false;}
			}
		}
		catch (SQLException ex)
		{ex.printStackTrace();}
		
		finally {
			closeConnection(conn);
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
