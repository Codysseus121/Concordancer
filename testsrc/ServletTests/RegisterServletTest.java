package ServletTests;

import javax.servlet.annotation.WebServlet;
import static org.junit.Assert.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;

import dp.servlets.concordancer.*;

/**
 * Servlet implementation class RegisterServletTest
 */
@WebServlet("/RegisterServletTest")
public class RegisterServletTest extends Mockito {
	
	@Test
	public void testCorrectRegistration() throws Exception {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	

		when(request.getParameter("username")).thenReturn(""); //Test an existing login & return true
		when(request.getParameter("password")).thenReturn("");
		when(request.getSession(true)).thenReturn(session);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RegisterServlet().processRequest(request, response);
		

		verify(request, atLeast(1)).getParameter("username"); //from https://stackoverflow.com/questions/5434419/how-to-test-my-servlet-using-junit
		writer.flush(); 
		assertTrue(stringWriter.toString().contains("True"));
	}

		@Test
		public void testFalseRegistration() throws Exception {
			
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);	

			when(request.getParameter("username")).thenReturn("test"); //Test an existing login & return true
			when(request.getParameter("password")).thenReturn("test");
			when(request.getSession(true)).thenReturn(session);

			StringWriter stringWriter = new StringWriter();
			PrintWriter writer = new PrintWriter(stringWriter);
			when(response.getWriter()).thenReturn(writer);

			new RegisterServlet().processRequest(request, response);
			

			verify(request, atLeast(1)).getParameter("username"); //from https://stackoverflow.com/questions/5434419/how-to-test-my-servlet-using-junit
			writer.flush();
			assertTrue(stringWriter.toString().contains("False"));

			
		
	}

}
