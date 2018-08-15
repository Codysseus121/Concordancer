package dp.concordancer.interfaces;

import java.util.List;
import java.util.TreeMap;

import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

public interface TextServiceInterface {

	List<Kwic> getKwic(String text, String filename, String query, int context);
	List<Kwic> getConcordances(User u, Project p, String query);
	List<Kwic> getCollocates(List<Kwic> concordances, List<String> permutations);
	TreeMap<String, Integer> generateIndex(Project project, User user);
	String moreContext(int findex, int lindex, int context, User u, Project p, String filename);
}
