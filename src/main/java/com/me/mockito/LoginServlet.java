package com.me.mockito;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -3780046696243508827L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Login Init called...");
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("Login Destroyed...");
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Login doPost...");
		String name = req.getParameter("user");
		String pwd = req.getParameter("password");
		String rememberMe = req.getParameter("rememberMe");
		System.out.println("User: " + name + " | password: " + pwd);
		if ("admin".equals(name) && "password".equals(pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", name);
			Cookie ck1 = new Cookie("user", name);
			Cookie ck2 = new Cookie("pwd", pwd);
			res.addCookie(ck1);
			res.addCookie(ck2);
			if (rememberMe != null && rememberMe != "") {
				Cookie ck3 = new Cookie("rememberMe", rememberMe);
				res.addCookie(ck3);
			}
		}
		PrintWriter out = res.getWriter();
		out.write("Login successfull...");
	}
}