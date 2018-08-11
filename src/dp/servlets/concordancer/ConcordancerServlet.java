package dp.servlets.concordancer;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.dao.concordancer.ConcordanceDao;
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
			
			if (user != null && project !=null) //check params for validity
			{
			
			ConcordanceDao cdao = new ConcordanceDao();
			Map<String, Integer> index = new TreeMap<String, Integer>();
			index = cdao.getIndex(project, user);
			session.setAttribute("index", index);
			dispatcher = getServletContext().getRequestDispatcher("/jsp/Concordances.jsp");
			dispatcher.forward(request, response);
			return;
			}
			
			else
			{
				response.getWriter().write("False");
			}
		} 
		
		catch (Exception e)
		{
		e.printStackTrace();
		}

	}
}
