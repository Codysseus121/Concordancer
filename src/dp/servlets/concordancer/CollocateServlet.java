package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.interfaces.FileDataAccessObject;

import dp.dao.concordancer.FileDao;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.SuffixArrayX;
import dp.model.concordancer.User;

/**
 * Servlet implementation class CollocateServlet
 * to handle requests for collocates.
 */
@WebServlet("/CollocateServlet")
public class CollocateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CollocateServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(true);
			String keyword1 = request.getParameter("keyword");
			String keyword2 = request.getParameter("keyword2");
			PrintWriter writer = response.getWriter();

			if (keyword1.length() != 0 || keyword2.length() != 0) {

				User user = (User) session.getAttribute("currentSessionUser");
				Project project = (Project) session.getAttribute("currentproject");
				List<Kwic> conc = getConcordances(user, project, keyword1);
				List<String> permutations = permute(keyword2);
				List<Kwic> collocates = new ArrayList<Kwic>();

				for (Kwic word : conc) { //check for collocates
					String lcontext = word.getLcontext();
					String rcontext = word.getRcontext();
					for (String perm : permutations) {
						if (lcontext.contains(perm) || rcontext.contains(perm))
							collocates.add(word);
					}
				}
				if (collocates.isEmpty()) { //no collocates found.

					writer.flush();
					writer.write("False");

				} else { //if found add to session to be used by the Concordances.jsp page.
					session.removeAttribute("concordances");
					session.setAttribute("concordances", collocates);
					writer.flush();
					writer.write("True");
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
 * Private method getConcordances to generate a list of the kwic lines for a query.
 * @param User user: the user sending the request.
 * @param Project project: the project to be processed.
 * @param String query: the pattern to be matched.
 */
	private List<Kwic> getConcordances(User u, Project p, String query) {

		int context = 55;// 55 characters on either side. Can be refactored according to user
							// requirements.
		List<Kwic> c = new ArrayList<Kwic>();
		List<Kwic> all = new ArrayList<Kwic>();
		FileDataAccessObject fdao = new FileDao();

		// read in text per file
		List<ProjectFile> files = fdao.getFiles(p, u);

		for (ProjectFile file : files) {

			c = getKwic(file, query, context);
			all.addAll(c);
		}

		return all;
	}
	/*
	 * Method getKwic to generate kwic lines.
	 * 
	 * @param ProjectFile file: the source text file.
	 * 
	 * @param String query: the pattern to be matched
	 * 
	 * @param int context: the number of characters for left and right context
	 * 
	 * @return: a List object.
	 */

	private List<Kwic> getKwic(ProjectFile file, String query, int context) {
		String text = file.getFilecontent();
		int n = file.getFilecontent().length();
		List<Kwic> words = new ArrayList<Kwic>();
		SuffixArrayX sa = new SuffixArrayX(text);
		List<String> perms = permute(query);

		// find all occurrences of queries with context and add to arraylist

		for (String permutation : perms) {

			for (int i = sa.rank(permutation); i < n; i++) {
				Kwic k = new Kwic();

				int from1 = sa.index(i);
				int to1 = Math.min(n, from1 + permutation.length());
				if (!permutation.equals(text.substring(from1, to1)))
					break;
				int from2 = Math.max(0, sa.index(i) - context);
				int to2 = Math.min(n, sa.index(i) + context + query.length());

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
	 * A method to calculate all possible case permutations of a String.
	 * 
	 * @Return: List. Author:
	 * https://www.geeksforgeeks.org/permute-string-changing-case/
	 * 
	 */
	private List<String> permute(String input) {

		List<String> permutations = new ArrayList<String>();
		int n = input.length();

		// Number of permutations is 2^n
		int max = 1 << n;

		// Converting string to lower case
		input = input.toLowerCase();

		// Using all subsequences and permuting them
		for (int i = 0; i < max; i++) {
			char combination[] = input.toCharArray();

			// If j-th bit is set, we convert it to upper case
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 1) == 1)
					combination[j] = (char) (combination[j] - 32);
			}

			permutations.add(String.valueOf(combination));

		}
		return permutations;
	}

	
}
