package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dp.model.concordancer.*;
import dp.concordancer.ConcFacade.*;


/**
 * Servlet implementation class RegisterServlet
 * for registering new users.
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
	 * Method processRequest to process all incoming requests for User registration.
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConcordancerFacade facade = new ConcFacadeImpl();
		
		PrintWriter writer = response.getWriter();
		User user = new User();

		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			

			if (username.length() == 0  || password.length() == 0) 
			{
				response.setContentType("text/html;charset=UTF-8");// sends response back to client to be handled by Ajax
				writer.flush();
				writer.write("False");
				
			} 
			
			
			else if (facade.checkUserName(username) == false)
			{
				response.setContentType("text/html;charset=UTF-8");// sends response back to client to be handled by Ajax
				writer.flush();
				writer.write("False");
			}
			
			else 
			{
				facade.registerUser(username, password);
				user = facade.getUser(username, password);
				List<Project> projects = facade.getProjects(user);
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);// sets this User object as session attribute
				session.setAttribute("projects", projects);// set this user's list of projects as session attribute
				response.setContentType("text/html;charset=UTF-8");// sends response back to client to be handled by
																	// Ajax
				writer.flush();
				writer.write("True");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}