package ServletTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import dp.servlets.concordancer.ProjectsServlet;
import dp.model.concordancer.*;

/**
 * Servlet implementation class ProjectsServletTest
 */
@WebServlet("/ProjectsServletTest")
public class ProjectsServletTest extends Mockito {

	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	HttpSession session = mock(HttpSession.class);	

	@Test
	public void testProjectsServlet() throws Exception {
		
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		UserInterface user = mock(User.class);
		ServletContext context = mock(ServletContext.class);
				
		
		when(request.getSession(true)).thenReturn(session);		
		when(session.getAttribute("currentSessionUser")).thenReturn(user);
		when(request.getServletContext()).thenReturn(context );
		when(request.getServletContext().getRequestDispatcher("/jsp/projects.jsp")).thenReturn(dispatcher);
		new ProjectsServlet().processRequest(request, response);
		
		  
		verify(dispatcher).forward(request, response);		
		

	}
}
