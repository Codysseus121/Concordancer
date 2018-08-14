package dp.servlets.concordancer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.concordancer.interfaces.FileDataAccessObject;

import dp.dao.concordancer.FileDao;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class ContextServlet
 * to handle requests for more context to a kwic line.
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
			PrintWriter writer = response.getWriter();
			Project project = (Project) session.getAttribute("currentproject");
			User user = (User) session.getAttribute("currentSessionUser");
			int clength = 200;
			String findex = request.getParameter("findex");
			String lindex = request.getParameter("lindex");

			if (findex.length() == 0 || lindex.length() == 0) {
				writer.flush();
				writer.write("False");
			}

			else {

				int index1 = Integer.parseInt(findex);
				int index2 = Integer.parseInt(lindex);
				String filename = request.getParameter("filename");

				String contextreq = moreContext(index1, index2, clength, user, project, filename);
				System.out.println(contextreq);

				response.setCharacterEncoding("UTF-8");// sends response back to client to be handled by
																	// Ajax
				writer.flush();
				writer.write(contextreq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * A method to generate more context for a given kwic line.
	 */
	private String moreContext(int findex, int lindex, int context, User u, Project p, String filename) {

		// Get content of file
		FileDataAccessObject fdao = new FileDao();
		String morecontext = "";
		String text = fdao.getFile(p, u, filename);
		if (findex - context >= 0 && lindex + context <= text.length()) {
			morecontext = text.substring(findex - context, lindex + context);
		}

		else if (findex - context < 0 && lindex + context <= text.length()) {
			morecontext = text.substring(0, lindex + context);
		}

		else if (lindex + context > text.length() && findex - context >= 0) {
			morecontext = text.substring(findex - context, text.length());
		} else {
			morecontext = text;
		}

		return morecontext;
	}

}
