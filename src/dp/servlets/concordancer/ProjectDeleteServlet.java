package dp.servlets.concordancer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.dao.concordancer.ProjectDao;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		response.setContentType("plain/text");
		//response.addHeader("Access-Control-Allow-Origin", "*");

		String pid = request.getParameter("parameter_pid");
		int project_id = Integer.parseInt(pid);
		User user = (User) session.getAttribute("currentSessionUser");
		deleteProject(user, project_id);

	}
	public void deleteProject(User u, int pid)
	{
		ProjectDataAccessObject pdao = new ProjectDao();
		pdao.deleteProject(u, pid);


	}
	}


