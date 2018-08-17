package dp.servlets.concordancer;

/*
 * Class LoginServlet to process requests for login by registered users.
 * @author: D.P.
 * @Date:July 2018
 */

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dp.model.concordancer.*;

import dp.concordancer.ConcFacade.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		processRequest(request, response);
	}

	/*
	 * Method processRequest to process all incoming login requests
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			response.setContentType("text/html");
			HttpSession session = request.getSession(true);
			String name = request.getParameter("username");// get parameters from request
			String pass = request.getParameter("password");
			

			ConcordancerFacade facade = new ConcFacadeImpl();
			UserInterface user = facade.getUser(name, pass);

			if (user != null) {
				session.setAttribute("currentSessionUser", user); // and set it as attribute for this session to be
																	// handled by JSP
				List<Project> projects = facade.getProjects(user); // Get this user's projects & set the List as attribute
				session.setAttribute("projects", projects);
				response.setContentType("text/html;charset=UTF-8");// sends response back to client to be handled by //
																	// Ajax on the client side
				response.getWriter().write("True");
			}

			else {

				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("False");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}