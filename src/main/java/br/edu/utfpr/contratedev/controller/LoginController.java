package br.edu.utfpr.contratedev.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.utfpr.contratedev.util.Constants;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String route = "/WEB-INF/view/login.jsp";
		request.getRequestDispatcher(route).forward(request, response);
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
			session.setAttribute("username", request.getUserPrincipal().getName());
			
			if(request.isUserInRole(Constants.ADMIN)) {
				String address = request.getContextPath() + "/a";
				response.sendRedirect(address);
			}
			else {
				String address = request.getContextPath() + "/u";
				response.sendRedirect(address);
			}
		}
		catch (Exception e) {
			response.sendRedirect("login?error");
		}
	}

}
