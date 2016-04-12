package com.me.mockito;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;

public class LoginServletTest extends TestCase {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@Mock
	RequestDispatcher rd;

	@Override
	@Before
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		Mockito.when(request.getParameter("user")).thenReturn("admin");
		Mockito.when(request.getParameter("password")).thenReturn("password");
		Mockito.when(request.getParameter("rememberMe")).thenReturn("Y");
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		Mockito.when(response.getWriter()).thenReturn(pw);

		new LoginServlet().doGet(request, response);

		// Verify the session attribute value
		Mockito.verify(session).setAttribute("user", "admin");

		String result = sw.getBuffer().toString().trim();
		System.out.println("Result: " + result);
		assertEquals("Login successfull...", result);
	}
}