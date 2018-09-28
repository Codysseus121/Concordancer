package dp.concordancer.interfaces;

import java.util.List;

/*
 * Interface TextServiceInterface with methods for generating kwic lines, collocates, context
 * and a frequency list/index.
 */
import java.util.TreeMap;

import dp.model.concordancer.KWICInterface;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;

public interface TextServiceInterface {

	List<KWICInterface> getKwic(String text, String filename, String query, int context);
	List<KWICInterface> getConcordances(UserInterface u, ProjectInterface p, String query);
	List<KWICInterface> getCollocates(List<KWICInterface> concordances, List<String> permutations);
	TreeMap<String, Integer> generateIndex(ProjectInterface project, UserInterface user);
	String moreContext(int findex, int lindex, int context, UserInterface u, ProjectInterface p, String filename);
}
