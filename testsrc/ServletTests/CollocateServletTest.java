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

import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;
import dp.servlets.concordancer.CollocateServlet;
import dp.servlets.concordancer.KWICServlet;

/**
 * Servlet implementation class CollocateServletTest
 */
@WebServlet("/CollocateServletTest")
public class CollocateServletTest extends Mockito {
	
	@Test
	public void testEmptyCollocate() throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		UserInterface user = null;
		ProjectInterface project = null;
		String word1 = "";
		String word2 = "";

		ServletContext context = mock(ServletContext.class);

		when(request.getParameter("keyword")).thenReturn(word1);
		when(request.getParameter("keyword2")).thenReturn(word2);
		when(request.getSession(true)).thenReturn(session);
		when(request.getServletContext()).thenReturn(context);
		when(request.getAttribute("currentproject")).thenReturn(project);
		when(request.getAttribute("currentSessionUser")).thenReturn(user);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CollocateServlet().processRequest(request, response);

		writer.flush();
		assertTrue(stringWriter.toString().contains("False"));

	}

	
	
}
