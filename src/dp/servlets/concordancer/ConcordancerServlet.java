package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dp.dao.concordancer.FileDao;
import dp.model.concordancer.*;

/**
 * Servlet implementation class ConcordancerServlet
 */
@WebServlet("/ConcordancerServlet")
public class ConcordancerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConcordancerServlet() {
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
			response.setContentType("text/html");
			RequestDispatcher dispatcher = null;
			Project project = (Project) session.getAttribute("currentproject");
			User user = (User) session.getAttribute("currentSessionUser");

			if (user != null && project != null) // check params for validity
			{

				TreeMap<String, Integer> index = generateIndex(project, user);//get Index and set it as attribute
				session.setAttribute("index", index);
				dispatcher = getServletContext().getRequestDispatcher("/jsp/Concordances.jsp");
				dispatcher.forward(request, response);
				return;
			}

			else {
				PrintWriter writer = response.getWriter();
				writer.flush();
				writer.write("False");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private TreeMap<String, Integer> generateIndex(Project project, User user) {

		// Get all Files for this Project.
		FileDao fdao = new FileDao();
		List<ProjectFile> files = fdao.getFiles(project, user);

		// Create a Map to store the index-frequency list
		TreeMap<String, Integer> index = new TreeMap<String, Integer>();
		List<String> words = new ArrayList<String>();// an ArrayList to store the tokens

		// Scanner object to tokenize text
		Scanner scanner = null;

		for (ProjectFile file : files) {
			String text = file.getFilecontent();
			text = processText(text);
			scanner = new Scanner(text);

			while (scanner.hasNext()) {
				String word = scanner.next().trim();
				words.add(word);
			}
		}
		// Take tokenized text and create index
		for (String word : words) {

			if (index.containsKey(word)) {
				index.put(word, index.get(word) + 1);

			}

			else {
				index.put(word, 1);
			}
		}

		return index;
	}

	private String processText(String text) {
		text = text.replaceAll("[\\p{Space}\\p{Punct}]", " ").trim();
		text = text.replaceAll("\\s{2,}", " ");
		text = text.replaceAll("[0-9]", " ");
		return text;
	}

}
