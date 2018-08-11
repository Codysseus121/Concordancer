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


import dp.model.concordancer.User;
import dp.servlets.concordancer.UseProjectServlet;


/**
 * Servlet implementation class UseProjectServletTest
 */
@WebServlet("/UseProjectServletTest")
public class UseProjectServletTest extends Mockito {
	
	@Test
	public void testCorrectProjectId() throws Exception {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		User user = new User();
		user.setUserid(2);
		ServletContext context = mock(ServletContext.class);
				
		
		when(request.getSession(true)).thenReturn(session);		
		when(session.getAttribute("currentSessionUser")).thenReturn(user);
		when(request.getServletContext()).thenReturn(context );
		when(request.getServletContext().getRequestDispatcher("/ConcordancerServlet")).thenReturn(dispatcher);
		when(request.getParameter("project_id")).thenReturn("123"); //Test an existing project_id & return true
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
			
		
		new UseProjectServlet().processRequest(request, response);
		
		writer.flush(); // it may not have been flushed yet...
		assertTrue(stringWriter.toString().contains("True"));
		
		
	}
	
	@Test
	public void testFalseProjectId() throws Exception {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		User user = new User();
		user.setUserid(2);
		ServletContext context = mock(ServletContext.class);
		String param="";
		
				
		
		when(request.getSession(true)).thenReturn(session);		
		when(session.getAttribute("currentSessionUser")).thenReturn(user);
		when(request.getServletContext()).thenReturn(context );
		when(request.getServletContext().getRequestDispatcher("/ConcordancerServlet")).thenReturn(dispatcher);
		when(request.getParameter("project_id")).thenReturn(param); //Test empty project_id
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
			
		
		new UseProjectServlet().processRequest(request, response);
		
		writer.flush();
		assertTrue(stringWriter.toString().contains("False"));
		
		
	}
	
	
	
}
