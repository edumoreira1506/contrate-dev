package br.edu.utfpr.contratedev.controller.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.JobDTO;
import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.JobMapper;
import br.edu.utfpr.contratedev.model.mapper.UserMapper;
import br.edu.utfpr.contratedev.model.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/g/usuarios/visualizar")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService userService = new UserService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.getById(id);
		UserDTO userDTO = UserMapper.toDTO(user);
		
		request.setAttribute("user", userDTO);
		
		String address = "/WEB-INF/view/manager/user.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

}
