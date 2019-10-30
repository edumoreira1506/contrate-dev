package br.edu.utfpr.contratedev.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.util.Constants;

/**
 * Servlet Filter implementation class WelcomeFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class WelcomePageFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String address = ((HttpServletRequest) request).getServletPath();
		
		if (!(address.equals("/") || address.equals(""))) {
			chain.doFilter(request, response);
		} else {

			HttpServletRequest req = (HttpServletRequest) request;

			if (req.getUserPrincipal() == null) {
				address = "login";
				((HttpServletResponse) response).sendRedirect(address);
			} else {
				if (req.isUserInRole(Constants.ADMIN)) {
					address = "a";
					((HttpServletResponse) response).sendRedirect(address);
				} else if (req.isUserInRole(Constants.USER)){
					address = "u";
					((HttpServletResponse) response).sendRedirect(address);
				} else {
					address = "g";
					((HttpServletResponse) response).sendRedirect(address);
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
