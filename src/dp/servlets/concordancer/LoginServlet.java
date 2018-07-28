package dp.servlets.concordancer;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import dp.model.concordancer.*;
import dp.dao.concordancer.*;
import dp.concordancer.forms.UserForm;
import dp.concordancer.interfaces.GetUserDataAccessObject;
import dp.concordancer.interfaces.ProjectDataAccessObject;

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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			response.setContentType("text/html");
			HttpSession session = request.getSession(true);
			User user = new User();
			UserForm u = new UserForm();
			GetUserDataAccessObject logindao = new LoginDao();
			String name = request.getParameter("username");
			String pass = request.getParameter("password");

			u = logindao.getUser(name, pass);
			
			if (u.getIsRegistered() == true) {

				user.setUserid(u.getUser_id());
				user.setUsername(u.getUsername());
				user.setPassword(u.getPassword());
				user.setIsRegistered(u.getIsRegistered());
				
				
				session.setAttribute("currentSessionUser", user);
				List<Project> projects = getProjects(user);
				session.setAttribute("projects", projects);				
		        response.setContentType("text/html;charset=UTF-8");//sends response back to client to be handled by Ajax
		        response.getWriter().write("True");
		  
			}

			else {
				response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write("False");
			
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	private List<Project> getProjects(User user)

	{
		ProjectDataAccessObject pdao = new ProjectDao();
		List<Project> projects = pdao.getProjects(user);// get a list of projects associated with this user. Array of
														// project objects. Setattribute the object. Go to next page
		
		return projects;
	}
}
