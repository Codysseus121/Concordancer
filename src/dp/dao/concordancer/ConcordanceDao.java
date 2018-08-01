package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

public class ConcordanceDao extends GetConnection {

	public Map<String, Integer> getIndex(Project project, User user) {

		Connection conn = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		Map<String, Integer> index = new TreeMap<String, Integer>();
		List<String> words = new ArrayList<String>();
		int project_id = project.getProject_id();
		String text = "";
		Scanner scanner = null;

		try {
			conn = getConnection();
			String sql = "SELECT file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND F.project_id="
					+ project_id + ";";
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();

			while (result.next()) {

				
				text = result.getString(1);				
				scanner = new Scanner(text).useDelimiter("[^a-zA-Z]*\\s+[^a-zA-Z]*");
				while (scanner.hasNext())
				{words.add(scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", "").trim());}
			}
				//Collections.sort(words);
				
				for (String word : words) {
					if (index.containsKey(word)) {
						index.put(word, index.get(word) + 1);

					}

					else {
						index.put(word, 1);
					}
				}
				
				
				
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);
			scanner.close();

		}
		return index;
	}

}
