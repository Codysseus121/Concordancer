package ServletTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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

import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;
import dp.servlets.concordancer.ConcordancerServlet;
import dp.servlets.concordancer.UseProjectServlet;

/**
 * Servlet implementation class ConcordancerServletTest
 */
@WebServlet("/ConcordancerServletTest")
public class ConcordancerServletTest extends Mockito {
	
       
	@Test
	public void testIndex() throws Exception {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		UserInterface user = null;
		ProjectInterface project = null;
		
		ServletContext context = mock(ServletContext.class);
				
		
		when(request.getSession(true)).thenReturn(session);				
		when(request.getServletContext()).thenReturn(context );
		when(request.getServletContext().getRequestDispatcher("/ConcordancerServlet")).thenReturn(dispatcher);
		when(request.getAttribute("currentproject")).thenReturn(project);
		when(request.getAttribute("currentSessionUser")).thenReturn(user);
		
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
			
		
		new ConcordancerServlet().processRequest(request, response);
		
		writer.flush(); 
		assertTrue(stringWriter.toString().contains("False"));
		
		
	}
}