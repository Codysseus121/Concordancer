package dp.servlets.concordancer;

import java.io.IOException;

import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.ConcFacade.ConcFacadeImpl;
import dp.concordancer.ConcFacade.ConcordancerFacade;

import dp.model.concordancer.*;

/**
 * Servlet implementation class ConcordancerServlet
 * to handle requests for the concordances.jsp page.
 * Produces an index/frequency list of the project's content
 * and forwards to the Concordances.jsp page.
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
			ConcordancerFacade service = new ConcFacadeImpl();
			ProjectInterface project = (ProjectInterface) session.getAttribute("currentproject");
			UserInterface user = (UserInterface) session.getAttribute("currentSessionUser");

			if (user != null && project != null) // check params for validity
			{

				TreeMap<String, Integer> index = service.generateIndex(project, user);//get Index and set it as attribute
				session.setAttribute("index", index);
				dispatcher = getServletContext().getRequestDispatcher("/jsp/Concordances.jsp");
				dispatcher.forward(request, response);
				return;
			}

			else {
				response.getWriter().write("False");				
			
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
