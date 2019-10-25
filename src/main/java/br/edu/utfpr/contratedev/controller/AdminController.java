package br.edu.utfpr.contratedev.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Sha256Generator;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/novo-admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User admin = new User();
		admin.setEmail("admin@admin.com");
		
		String hash = Sha256Generator.generate("qwerty");
		admin.setPassword(hash);
		
		Role role = new Role("admin@admin.com", Constants.ADMIN); 
		
		userService.saveUserAndRole(admin, role);
	}

}
