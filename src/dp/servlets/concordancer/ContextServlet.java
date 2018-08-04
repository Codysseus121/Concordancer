package dp.servlets.concordancer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.dao.concordancer.ConcordanceDao;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class ContextServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
	
		Project project = (Project) session.getAttribute("currentproject");		
		User user = (User) session.getAttribute("currentSessionUser");
		int clength = 200;
		String findex = request.getParameter("findex");
		String lindex = request.getParameter("lindex");
		int index1 = Integer.parseInt(findex);
		int index2 = Integer.parseInt(lindex);		
		String filename = request.getParameter("filename");
		System.out.println("ContextServlet " + filename + " " + index1 + " " +index2);
		ConcordanceDao cdao = new ConcordanceDao();
		
		String contextreq = cdao.moreContext(index1, index2, clength, user, project, filename);
		//System.out.println(contextreq);
		response.setContentType("text/html;charset=UTF-8");//sends response back to client to be handled by Ajax
        response.getWriter().write(contextreq);
		
		
	}

}
