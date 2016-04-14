package com.me.mockito;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -3058656694893138350L;

	private String greeting = "Hello World";

	public HelloServlet() {
	}

	public HelloServlet(String greeting) {
		this.greeting = greeting;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>" + greeting + "</h1>");
		response.getWriter().println(String.format("%s://%s:%d%s?%s", request.getScheme(), request.getServerName(),
				request.getServerPort(), request.getRequestURI(), request.getQueryString()));
		response.getWriter().println("session=" + request.getSession(true).getId());
	}
}