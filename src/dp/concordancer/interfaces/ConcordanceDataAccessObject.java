package dp.concordancer.interfaces;

import java.util.List;
import java.util.Map;

import dp.model.concordancer.*;

public interface ConcordanceDataAccessObject {

	public Map<String, Integer> getIndex(Project project, User user);
	public List<Kwic> getConcordances (User u, Project p, String query);
	public List<Kwic> getKwic (ProjectFile file, String query, int context);
	public List<String> permute(String input);
	public String moreContext (int findex, int lindex, int context, User u, Project p, String filename);
	public List<Kwic> getCollocates();
	
	
}
