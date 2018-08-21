package dp.servlets.concordancer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dp.model.concordancer.KWICInterface;

/**
 * Servlet implementation class PaginateServlet
 */
@WebServlet("/PaginateServlet")
public class PaginateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaginateServlet() {
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

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("PagServlet accessed");
			HttpSession session = request.getSession(true);
			@SuppressWarnings("unchecked")
			List<KWICInterface> conc = (List<KWICInterface>) session.getAttribute("concordances");
			int page = 24;
			String index = "";
			index = request.getParameter("index");
			int indexvalue = 0;
			if (index != null) {
				indexvalue = Integer.parseInt(index);
				indexvalue = indexvalue - 1;
				System.out.println(indexvalue);
			}

			String direction = request.getParameter("dir");

			if (direction == null)
				direction = "";

			int pageStart = 0;
			int pageEnd = 0;

			
			System.out.println("Direction " + direction);

			if (conc.isEmpty()) {
				response.getWriter().write("False");
				session.setAttribute("pageStart", pageStart);
				session.setAttribute("pageEnd", pageEnd);
			}

			else {
				int size = conc.size()-1;
				switch (direction) {

				case "next":
					pageStart = indexvalue + page;
					pageEnd = pageStart + page;
					System.out.println(pageStart + " " + size);

					if (pageStart < size && pageStart + page < size) // if it can fit two pages
					{
						pageStart = pageStart + 1;
						pageEnd = pageStart + page;
						System.out.println("1st case" + pageStart + " " + pageEnd);
					} 
					else if (pageStart < size && pageStart + page > size)//if word men
					{
						pageStart = pageStart + 1;
						pageEnd = size;
						System.out.println("2nd case" + pageStart + " " + pageEnd);
					}

					else if (pageStart >= size) { //the missing = sign caused a bug for equality cases word calling
						pageStart = indexvalue;
						pageEnd = size;
						System.out.println("3rd case" + pageStart + " " + pageEnd);
						
					}								
					
						
					break;

				case "previous":
					if (indexvalue == 0) 
					{
						pageStart = 0;
						pageEnd = page;
						System.out.println("Back 1" + pageStart + " " + pageEnd);
					} 
					else
					{
						System.out.println("Back current " + pageStart + " " + pageEnd);
						pageStart = indexvalue - page-1;
						pageEnd = pageStart + page;
						System.out.println("Back 2 " + pageStart + " " + pageEnd);
					}
					break;
				default:
					pageStart = 0;
					pageEnd = 24;
					break;

				}
				session.setAttribute("pageStart", pageStart);
				session.setAttribute("pageEnd", pageEnd);
				response.getWriter().write("True");
			}

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
