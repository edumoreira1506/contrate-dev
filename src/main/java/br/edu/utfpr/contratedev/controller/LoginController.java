package br.edu.utfpr.contratedev.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole(Constants.ADMIN)) {
			response.sendRedirect("a/home");
		} else if(request.isUserInRole(Constants.MANAGER)) {
			response.sendRedirect("g/home");
		} else if(request.isUserInRole(Constants.USER)) {
			response.sendRedirect("u/home");
		} else {
			String route = "/WEB-INF/view/login.jsp";
			request.getRequestDispatcher(route).forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			request.login(email, password);

			HttpSession session = request.getSession();
			String username = request.getUserPrincipal().getName();
			session.setAttribute("username", username);
			
			String address = request.getContextPath();
			if(request.isUserInRole(Constants.ADMIN)) {
				session.setAttribute("role", "a");
				address += "/a";
			} else if(request.isUserInRole(Constants.MANAGER)) {
				address += "/g";
				session.setAttribute("role", "g");
			} else {
				session.setAttribute("role", "u");
				address += "/u";
			}
			
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
			Cookie c = new Cookie("lastLogin", dateFormat.toString());
			c.setMaxAge(60 * 60 * 24 * 10);
			
			User user = userService.getById(username);
			Cookie nome = new Cookie("name", user.getName());
			
			response.addCookie(nome);
			response.addCookie(c);
			response.sendRedirect(address);
		}
		catch (Exception e) {
			response.sendRedirect("login?error");
		}
	}

}
