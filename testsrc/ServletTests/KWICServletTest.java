package ServletTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import dp.dao.concordancer.*;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;
import dp.servlets.concordancer.ConcordancerServlet;
import dp.servlets.concordancer.KWICServlet;

/**
 * Servlet implementation class KWICServletTest
 */
@WebServlet("/KWICServletTest")
public class KWICServletTest extends Mockito {

	@Test
	public void testEmptyKwic() throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		User user = null;
		Project project = null;
		String word = "";

		ServletContext context = mock(ServletContext.class);

		when(request.getParameter("keyword")).thenReturn(word);
		when(request.getSession(true)).thenReturn(session);
		when(request.getServletContext()).thenReturn(context);
		when(request.getAttribute("currentproject")).thenReturn(project);
		when(request.getAttribute("currentSessionUser")).thenReturn(user);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new KWICServlet().processRequest(request, response);

		writer.flush();
		assertTrue(stringWriter.toString().contains("False"));

	}

}
