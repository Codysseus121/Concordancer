package dp.servlets.concordancer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.dao.concordancer.ProjectDao;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class UseProjectServlet
 */
@WebServlet("/UseProjectServlet")
public class UseProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UseProjectServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try
		{
			HttpSession session = request.getSession(true);
			RequestDispatcher dispatcher = null;
			User user = (User) session.getAttribute("currentSessionUser");
			String project_id="";
			project_id= request.getParameter(project_id);
			int projectID = Integer.parseInt(project_id);
			ProjectDataAccessObject pdao = new ProjectDao();
			Project p= new Project();
			p = pdao.getProject(projectID, user);
			session.setAttribute("currentProject", p);
			dispatcher = getServletContext().getRequestDispatcher("/ConcordancerServlet");
			dispatcher.forward(request, response);
			return;
			
			
		}
	 catch (Exception e) 
	{
		System.out.println("Exception:" + e);
	}
}
}
