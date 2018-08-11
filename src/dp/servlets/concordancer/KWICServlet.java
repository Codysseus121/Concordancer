package dp.servlets.concordancer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import dp.dao.concordancer.ConcordanceDao;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class KWICServlet
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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession(true);
			String word = request.getParameter("keyword");
			word = word.trim();
			User user = (User) session.getAttribute("currentSessionUser");
			Project project = (Project) session.getAttribute("currentproject");
			ConcordanceDao cdao = new ConcordanceDao();
			List<Kwic> conc = cdao.getConcordances(user, project, word);
			if (conc.isEmpty()) {
				response.getWriter().write("False");
			} else {
				session.setAttribute("concordances", conc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
