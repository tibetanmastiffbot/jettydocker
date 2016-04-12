package com.me;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.jackson.User;
import com.me.mockito.HelloServlet;
import com.me.mockito.LoginServlet;
import com.me.remoteproxy.ProxyResourceClient;
import com.me.remoteproxy.ResourceIF;

public class JettyMain extends AbstractHandler {

	public static void main(String[] args) throws Exception {
		Server server = new Server(9080);
		LoginService loginService = new HashLoginService("MyRealm", "src/test/resources/realm.properties");
		server.addBean(loginService);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		ConstraintSecurityHandler security = new ConstraintSecurityHandler();
		context.setSecurityHandler(security);
		server.setHandler(context);

		Constraint constraint = new Constraint();
		constraint.setName("auth");
		constraint.setAuthenticate(true);
		constraint.setRoles(new String[] { "user", "admin" });

		ConstraintMapping mapping = new ConstraintMapping();
		mapping.setPathSpec("/*");
		mapping.setConstraint(constraint);

		security.setConstraintMappings(Collections.singletonList(mapping));
		security.setAuthenticator(new BasicAuthenticator());
		security.setLoginService(loginService);

		// security.setHandler(new JettyMain());
		context.addServlet(new ServletHolder(LoginServlet.class), "/l");
		context.addServlet(new ServletHolder(HelloServlet.class), "/h");

		server.start();

		Thread.sleep(1000);
		ResourceIF proxy = new ProxyResourceClient();
		System.out.println(proxy.sayHello());

		server.join();
	}

	private static void doJackson() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		User user = mapper.readValue(new File("test/user.json"), User.class);
		System.out.println(user);
		user.getName().setFirst("Mad");
		mapper.writeValue(new File("test/user-modified.log"), user);
		System.out.println(mapper.writeValueAsString(user));
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
	}

}
