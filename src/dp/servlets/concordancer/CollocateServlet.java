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

import dp.concordancer.ConcFacade.ConcFacadeImpl;
import dp.concordancer.ConcFacade.ConcordancerFacade;
import dp.concordancer.ConcFacade.TextService;
import dp.model.concordancer.KWICInterface;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;

/**
 * Servlet implementation class CollocateServlet
 * to handle requests for collocates.
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
			String keyword1 = request.getParameter("keyword");
			String keyword2 = request.getParameter("keyword2");
			PrintWriter writer = response.getWriter();
			ConcordancerFacade service = new ConcFacadeImpl();
			TextService textservice = new TextService();

			if (keyword1.length() != 0 || keyword2.length() != 0) {

				UserInterface user = (UserInterface) session.getAttribute("currentSessionUser");
				ProjectInterface project = (ProjectInterface) session.getAttribute("currentproject");
				
				List<KWICInterface> conc = service.getConcordances(user, project, keyword1);
				List<String> permutations = textservice.permute(keyword2);
				List<KWICInterface> collocates = service.getCollocates(conc, permutations);				

				
				if (collocates.isEmpty()) { //no collocates found.

					writer.flush();
					writer.write("False");

				} else { //if found add to session to be used by the Concordances.jsp page.
					session.removeAttribute("concordances");
					session.setAttribute("concordances", collocates);
					writer.flush();
					writer.write("True");
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
