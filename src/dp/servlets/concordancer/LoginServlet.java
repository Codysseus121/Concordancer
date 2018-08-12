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
			

			User user = getUser(name, pass);

			if (user != null) {
				session.setAttribute("currentSessionUser", user); // and set it as attribute for this session to be
																	// handled by JSP
				List<Project> projects = getProjects(user); // Get this user's projects & set the List as attribute
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

	/*
	 * Method getProjects() to get all the projects of this User.
	 */
	public List<Project> getProjects(User user)

	{
		ProjectDataAccessObject pdao = new ProjectDao();
		List<Project> projects = pdao.getProjects(user);// get a list of projects associated with this user. Array of
														// project objects. Set attribute the object. Go to next page

		return projects;
	}

	public User getUser(String username, String password) {

		if (username.length()==0 || password.length()==0)
		{
			return null;
		}
		else
		{
		
		GetUserDataAccessObject userdao = new GetUserDao();
		User user = new User();
		UserForm u = new UserForm(); // not serializable user object to check parameters for validity.
		u = userdao.getUser(username, password);// call getUser method of LoginDao to find if user is registered.

		if (u.getIsRegistered() == true) { // Create User object

			user.setUserid(u.getUser_id());
			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			user.setIsRegistered(u.getIsRegistered());
			return user;

		}

		else {
			user = null;
		}
		
		return user;
		}

	}

}
