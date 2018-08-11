package dp.servlets.concordancer;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ProjectDeleteServlet
 */
@WebServlet("/ProjectDeleteServlet")
public class ProjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectDeleteServlet() {
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
	 * Method processRequest to process all incoming requests for project deletion.
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		response.setContentType("plain/text");
		String pid = request.getParameter("parameter_pid");// get the project's key parameter from the client

		try {

			if (pid.length() > 0) {

				int project_id = Integer.parseInt(pid);// convert it into int.
				User user = (User) session.getAttribute("currentSessionUser");// get the session user.
				deleteProject(user, project_id);// delete project from the database

				// refresh list of projects after deletion

				ProjectDataAccessObject pdao = new ProjectDao();
				List<Project> projects = pdao.getProjects(user);
				session.setAttribute("projects", projects);
				response.getWriter().write("True");
			} else {
				response.getWriter().write("False");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * private method deleteProject() to delete a project from the persistence
	 * layer.
	 * 
	 * @User: the user
	 * 
	 * @int id: the key of the project
	 */

	private void deleteProject(User u, int pid) {
		ProjectDataAccessObject pdao = new ProjectDao();
		pdao.deleteProject(u, pid);

	}
}
