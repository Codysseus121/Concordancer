package dp.servlets.concordancer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.dao.concordancer.ConcordanceDao;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class CollocateServlet
 */
@WebServlet("/CollocateServlet")
public class CollocateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollocateServlet() {
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
		String keyword1 = request.getParameter("keyword");
		String keyword2 = request.getParameter("keyword2");
		User user = (User) session.getAttribute("currentSessionUser");
		Project project = (Project) session.getAttribute("currentproject");
		ConcordanceDao cdao = new ConcordanceDao();
		List<Kwic> conc = cdao.getConcordances(user, project, keyword1);
		List<String> permutations = cdao.permute(keyword2);
		List<Kwic> collocates = new ArrayList<Kwic>();
		
		for (Kwic word :conc)
		{
			String lcontext = word.getLcontext();
			String rcontext = word.getRcontext();
			for (String perm : permutations) 
			{
			if (lcontext.contains(perm) || rcontext.contains(perm))
				collocates.add(word);
			}
		}
		if (collocates.isEmpty())
		{
			response.getWriter().write("False");
		}
		
		
		
		session.removeAttribute("concordances");
		session.setAttribute("concordances", collocates);
		
				
		
	}

}
