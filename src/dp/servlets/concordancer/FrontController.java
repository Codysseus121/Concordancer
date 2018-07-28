package dp.servlets.concordancer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;

import java.io.IOException;


/**
 * Servlet implementation class FrontController
 */
@WebServlet(name = "/FrontControllerServlet", urlPatterns = "/concordancer")
@MultipartConfig(maxFileSize = 10485760L)

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processrequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processrequest(request, response);
	}

	protected void processrequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		// response.addHeader("Access-Control-Allow-Origin", "*");
		RequestDispatcher dispatcher = null;
		String base = "/jsp/";
		String url = base + "/home.jsp";
		// System.out.println("Menu " + action);

		HttpSession session = request.getSession(true);

		if (action != null) {

			switch (action) {

			case "login":

				url = "/LoginServlet";
				return;

			}

		}
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		return;

	}
}
