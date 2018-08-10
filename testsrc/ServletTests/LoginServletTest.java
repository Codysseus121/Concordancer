package ServletTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import dp.concordancer.forms.UserForm;
import dp.dao.concordancer.LoginDao;
import dp.model.concordancer.*;
import dp.servlets.concordancer.LoginServlet;

/**
 * Servlet implementation class LoginServletTest
 */
@WebServlet("/LoginServletTest")
public class LoginServletTest extends Mockito {

	@Test
	public void testCorrectLogin() throws Exception {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);	

		when(request.getParameter("username")).thenReturn("test"); //Test an existing login & return true
		when(request.getParameter("password")).thenReturn("test");
		when(request.getSession(true)).thenReturn(session);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new LoginServlet().processRequest(request, response);
		

		verify(request, atLeast(1)).getParameter("username"); //from https://stackoverflow.com/questions/5434419/how-to-test-my-servlet-using-junit
		writer.flush(); // it may not have been flushed yet...
		assertTrue(stringWriter.toString().contains("True"));
	}

		
}
