package dp.dao.concordancer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import dp.concordancer.interfaces.ConcordanceDataAccessObject;
import dp.concordancer.interfaces.FileDataAccessObject;
import dp.model.concordancer.*;


/* Class ConcordanceDao: a class providing methods to access the DB and generate
 * concordances, collocates and more context for kwic lines. Extends superclass
 * GetConnection and implements interface ConcordanceDataAccessObject.
 * @author: D.P.
 * @Date:August 2018
 * 
 * 
 */

public class ConcordanceDao extends GetConnection implements ConcordanceDataAccessObject {

/*
 * 	
 */
	
	public Map<String, Integer> getIndex(Project project, User user) {

		Connection conn = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		Map<String, Integer> index = new TreeMap<String, Integer>();
		List<String> words = new ArrayList<String>();
		int project_id = project.getProject_id();
		String text = "";
		Scanner scanner = null;
		
		FileDao fdao = new FileDao();

		try {
			conn = getConnection();
			String sql = "SELECT file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND F.project_id="
					+ project_id + ";";
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();

			while (result.next()) {

				
				text = result.getString(1);
				text = fdao.processText(text);
				scanner = new Scanner(text);
				while (scanner.hasNext())
				{	
					String word = scanner.next().trim();
					words.add(word);}
				}
				
				
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
		    	FileDataAccessObject fdao = new FileDao();
		    	
		    	
		        // read in text per file
		        List<ProjectFile> files = fdao.getFiles(p, u);
		        
		        for (ProjectFile file : files)
		        {
		          
		           c = getKwic(file, query, context);       
		           all.addAll(c);
		            }
		        
		            
		        return all;
		    } 
	
	public List<Kwic> getKwic(ProjectFile file, String query, int context)
	{
		String text = file.getFilecontent();
		int n = file.getFilecontent().length();
		List<Kwic> words = new ArrayList<Kwic>();
		SuffixArrayX sa = new SuffixArrayX(text);
		List<String> perms = permute(query);
        

        // find all occurrences of queries with context and add to arraylist
        
		for (String permutation : perms) {
           
            for (int i = sa.rank(permutation); i < n; i++)
            {
            	Kwic k = new Kwic();
                
                int from1 = sa.index(i);
                int to1   = Math.min(n, from1 + permutation.length());
                if (!permutation.equals(text.substring(from1, to1))) break;
                int from2 = Math.max(0, sa.index(i) - context);
                int to2   = Math.min(n, sa.index(i) + context + query.length());
               
                k.setKeyword(permutation);
                k.setFilename(file.getFile_name());
                k.setLcontext(text.substring(from2, from1));
                k.setRcontext(text.substring(to1, to2));
                k.setIndex1(from1);
                k.setIndex2(to1);
                words.add(k);
                
            }
		}
           return words;
	}
	/*
	 * https://www.geeksforgeeks.org/permute-string-changing-case/
	 */
	
	public List<String> permute(String input)
    {
        
		List<String> permutations = new ArrayList<String>();
		int n = input.length();
         
        // Number of permutations is 2^n
        int max = 1 << n;
         
        // Converting string to lower case
        input = input.toLowerCase();
         
        // Using all subsequences and permuting them
        for(int i = 0;i < max; i++)
        {
            char combination[] = input.toCharArray();
             
            // If j-th bit is set, we convert it to upper case
            for(int j = 0; j < n; j++)
            {
                if(((i >> j) &  1) == 1)
                    combination[j] = (char) (combination[j]-32);
            }
             
            
            permutations.add(String.valueOf(combination));
            
        }
        return permutations;
    }
	
	
	
	
	public String moreContext (int findex, int lindex, int context, User u, Project p, String filename)
	{
		
		//Get content of file
    	FileDataAccessObject fdao = new FileDao();
    	String morecontext = "";
    	String text = fdao.getFile(p, u, filename);
        if (findex-context>=0 && lindex+context<=text.length())
        {
        	morecontext=text.substring(findex-context, lindex+context);		
        }
        
        else if(findex-context<0 && lindex+context<=text.length())
        {
        	morecontext=text.substring(0, lindex+context);
        }
    	
        else if (lindex+context>text.length() && findex-context>=0)
        {
        	morecontext = text.substring(findex-context, text.length());
        }
        else
        {
        	morecontext=text;
        }
    			
		return morecontext;
	}

	public List<Kwic> getCollocates()
	{
		List<Kwic> collocates = new ArrayList<Kwic>();
		
		return collocates;
	}
}
