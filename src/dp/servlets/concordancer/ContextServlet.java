package dp.servlets.concordancer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.ConcFacade.TextService;
import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;

/**
 * Servlet implementation class ContextServlet to handle requests for more
 * context to a kwic line.
 */
@WebServlet("/ContextServlet")
public class ContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContextServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(true);
			ProjectInterface project = (ProjectInterface) session.getAttribute("currentproject");
			UserInterface user = (UserInterface) session.getAttribute("currentSessionUser");
			int clength = 200;
			String findex = request.getParameter("findex");
			String lindex = request.getParameter("lindex");
			TextService textservice = new TextService();

			if (findex.length() == 0 || lindex.length() == 0) {
				
				response.getWriter().write("False");
			}

			else {

				int index1 = Integer.parseInt(findex);
				int index2 = Integer.parseInt(lindex);
				String filename = request.getParameter("filename");
				String contextreq = textservice.moreContext(index1, index2, clength, user, project, filename);
				

				response.setContentType("text/html;charset=UTF-8");// sends response back to client to be handled by
																	// Ajax
				response.getWriter().write(contextreq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
