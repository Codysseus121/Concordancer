package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	/*
	 * method processRequest to handle all incoming requests to set a specific
	 * project as session attribute.
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		PrintWriter writer = response.getWriter();
		session.removeAttribute("currentproject"); // clear previous session objects
		session.removeAttribute("concordances");
		RequestDispatcher dispatcher = null;
		User user = (User) session.getAttribute("currentSessionUser");

		try // get project and add it to the session
		{
			ProjectDataAccessObject pdao = new ProjectDao();
			Project p = null;
			Integer projectID = null;

			// get attributes
			String projectpara = "";
			projectpara = request.getParameter("project_id");
			
			if (projectpara.length()>0) //check param for validity
			{
				projectID = Integer.parseInt(projectpara);

			// get project, set it as session attribute and forward.

			p = pdao.getProject(projectID, user);
		
			
			if (p != null)
			{
			session.setAttribute("currentproject", p);			
			response.setContentType("text/html;charset=UTF-8");
			writer.flush();
			writer.write("True");
			dispatcher = getServletContext().getRequestDispatcher("/ConcordancerServlet");
			dispatcher.forward(request, response);
			return;
			}
			}
			
			else
				
			{
				writer.flush();
				writer.write("False");
			}
		}

		catch (Exception e) {
			e.printStackTrace();;
		}

	}
}
