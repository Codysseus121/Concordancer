package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import dp.concordancer.interfaces.FileDataAccessObject;
import dp.dao.concordancer.FileDao;
import dp.model.concordancer.*;

/**
 * Servlet implementation class KWICServlet
 * to handle requests for kwic lines.
 */
@WebServlet("/KWICServlet")
public class KWICServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KWICServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession(true);
			PrintWriter writer = response.getWriter();
			String word = request.getParameter("keyword");
			word = word.trim();

			if (word.length() == 0) // check validity

			{
				writer.flush();
				writer.write("False");

			}

			else {

				User user = (User) session.getAttribute("currentSessionUser");
				Project project = (Project) session.getAttribute("currentproject");
				Concordances conc = new Concordances(getConcordances(user, project, word));
				

				if (conc.getConcordances().isEmpty()) {
					writer.flush();
					writer.write("False");
				}

				else {
					session.setAttribute("concordances", conc);
					writer.flush();
					writer.write("True");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method getConcordances to get all kwics in context for a Project
	 * 
	 * @return: List<Kwic> object with the kwics in context.
	 * 
	 */
	public List<Kwic> getConcordances(User u, Project p, String query) {
		
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
	 * @Return: List. 
	 * @Author: https://www.geeksforgeeks.org/permute-string-changing-case/
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
