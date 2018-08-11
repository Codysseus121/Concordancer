package ServletTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;
import dp.servlets.concordancer.CollocateServlet;
import dp.servlets.concordancer.ContextServlet;

/**
 * Servlet implementation class ContextServletTest
 */
@WebServlet("/ContextServletTest")
public class ContextServletTest extends Mockito {
	@Test
	public void testEmptyCollocate() throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		User user = null;
		Project project = null;
		String findex = "";
		String lindex = "";

		ServletContext context = mock(ServletContext.class);

		when(request.getParameter("findex")).thenReturn(findex);
		when(request.getParameter("lindex")).thenReturn(lindex);
		when(request.getSession(true)).thenReturn(session);
		when(request.getServletContext()).thenReturn(context);
		when(request.getAttribute("currentproject")).thenReturn(project);
		when(request.getAttribute("currentSessionUser")).thenReturn(user);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new ContextServlet().processRequest(request, response);

		writer.flush();
		assertTrue(stringWriter.toString().contains("False"));

	}

	
	


}
