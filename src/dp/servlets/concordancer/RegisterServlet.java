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

import dp.dao.concordancer.LoginDao;
import dp.dao.concordancer.*;
import dp.model.concordancer.*;
import dp.concordancer.forms.*;
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
		
		RequestDispatcher dispatcher = null;
		RegisterDao rdao = new RegisterDao();
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
					


			if (rdao.checkUserName(username) == false) {
				dispatcher = getServletContext().getRequestDispatcher("/jsp/AlreadyRegistered.jsp");
				dispatcher.forward(request, response);
				return;	}
			else
				{
				rdao.registerUser(username, password);
				UserForm u = rdao.getUser(username, password);
				//List<Project> projects = getProjects(u);
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", u);
				//session.setAttribute("projects",projects);
				}
			dispatcher = getServletContext().getRequestDispatcher("/jsp/projects.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			System.out.println("Exception" + e);
		}}
}