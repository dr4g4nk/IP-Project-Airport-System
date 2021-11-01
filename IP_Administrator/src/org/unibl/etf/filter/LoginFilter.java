package org.unibl.etf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.beans.AdminBean;

@WebFilter("*.xhtml")
public class LoginFilter implements Filter {

	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		AdminBean admin = (session != null) ? (AdminBean) session.getAttribute("adminBean") : null;

		if ((session != null && (!request.getRequestURI().contains("IP_Administrator/") || !request.getRequestURI().endsWith("login.xhtml"))) && (admin == null || !admin.getAdmin().isLoggedIn())) {
			System.out.println("redirekcija");
			response.sendRedirect("login.xhtml"); // No logged-in user found, so redirect to login page.
		} else {
			chain.doFilter(req, res); // Logged-in user found, so just continue request.
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
