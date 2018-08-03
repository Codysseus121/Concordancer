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

import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.SuffixArrayX;
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
				{words.add(scanner.next().replaceAll("[^a-zA-Z]", " ").trim());}
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
	
	public List<Kwic> getConcordances (User u, Project p, String query)
	{  		       
		    	int context = 55;
		    	List <Kwic> c = new ArrayList<Kwic>();
		    	List <Kwic> all = new ArrayList<Kwic>();
		    	ProjectDao pdao = new ProjectDao();
		    	
		    	
		        // read in text per file
		        List<ProjectFile> files = pdao.getFiles(p, u);
		        
		        for (ProjectFile file : files)
		        {
		          
		           c = getKwic(file, query, context);       
		           all.addAll(c);
		            }
		        
		            
		        return all;
		    } 
	
	private List<Kwic> getKwic(ProjectFile file, String query, int context)
	{
		String text = file.getFilecontent();
		int n = file.getFilecontent().length();
		List<Kwic> words = new ArrayList<Kwic>();
		SuffixArrayX sa = new SuffixArrayX(text);
        

        // find all occurrences of queries with context and add to arraylist
        
           
            for (int i = sa.rank(query); i < n; i++)
            {
            	Kwic k = new Kwic();
                
                int from1 = sa.index(i);
                int to1   = Math.min(n, from1 + query.length());
                if (!query.equals(text.substring(from1, to1))) break;
                int from2 = Math.max(0, sa.index(i) - context);
                int to2   = Math.min(n, sa.index(i) + context + query.length());
               
                k.setKeyword(query);
                k.setFilename(file.getFile_name());
                k.setLcontext(text.substring(from2, from1));
                k.setRcontext(text.substring(to1, to2));
                k.setKeyword(query);
                words.add(k);
                
            }
           return words;
	}
	
	public String moreContext (String query, ProjectFile file, int context)
	{
		String morecontext="";
		
		
		
		return morecontext;
	}

}
