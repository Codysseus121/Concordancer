package dp.servlets.concordancer;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ProjectsServlet
 */
@WebServlet("/ProjectsServlet")
public class ProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectsServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		HttpSession session = request.getSession(true);
		
		RequestDispatcher dispatcher = null;
		User user = (User) session.getAttribute("currentSessionUser");

		try // get project and add it to the session
		{
			ProjectDataAccessObject pdao = new ProjectDao();
			List <Project> projects = pdao.getProjects(user);	
			session.setAttribute("projects", projects);
			dispatcher = getServletContext().getRequestDispatcher("/jsp/projects.jsp");
			dispatcher.forward(request, response);
			return;

		}

		catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
}
