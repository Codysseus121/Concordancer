package dp.servlets.concordancer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.dao.concordancer.*;
import dp.model.concordancer.*;
import dp.concordancer.forms.*;
import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.concordancer.interfaces.RegisterDataAccessObject;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
	
		RegisterDataAccessObject rdao = new RegisterDao();
		User user = new User();
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
					


			if (rdao.checkUserName(username) == false) {
				 response.setContentType("text/html;charset=UTF-8");//sends response back to client to be handled by Ajax
			     response.getWriter().write("False");
					}
			else
				{
				rdao.registerUser(username, password);
				UserForm u = rdao.getUser(username, password);
				user.setUserid(u.getUser_id());
				user.setUsername(u.getUsername());
				user.setPassword(u.getPassword());
				user.setIsRegistered(u.getIsRegistered());
				List<Project> projects = getProjects(user);
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				session.setAttribute("projects",projects);
				response.setContentType("text/html;charset=UTF-8");//sends response back to client to be handled by Ajax
			    response.getWriter().write("True");
				}
			
		} catch (Exception e) {
			System.out.println("Exception" + e);
		}}
	private List<Project> getProjects(User user)

	{
		ProjectDataAccessObject pdao = new ProjectDao();
		List<Project> projects = pdao.getProjects(user);
														
		
		return projects;
	}
}