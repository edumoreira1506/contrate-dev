package br.edu.utfpr.contratedev.filter;

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

import br.edu.utfpr.contratedev.util.Constants;

/**
 * Servlet Filter implementation class EditPageFilter
 */
@WebFilter("/editar-perfil")
public class EditPageFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public EditPageFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String address = ((HttpServletRequest) request).getServletPath();

		if (req.getUserPrincipal() == null) {
			address = "login";
			((HttpServletResponse) response).sendRedirect(address);
		} else {
			if (req.isUserInRole(Constants.ADMIN)) {
				address = "a/editar-perfil";
				((HttpServletResponse) response).sendRedirect(address);
			} else if (req.isUserInRole(Constants.USER)){
				address = "u/editar-perfil";
				((HttpServletResponse) response).sendRedirect(address);
			} else {
				address = "g/editar-perfil";
				((HttpServletResponse) response).sendRedirect(address);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
