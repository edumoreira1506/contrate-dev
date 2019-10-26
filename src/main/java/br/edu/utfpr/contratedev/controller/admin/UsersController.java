package br.edu.utfpr.contratedev.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.error.ParamException;
import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.UserMapper;
import br.edu.utfpr.contratedev.model.service.RoleService;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Routes;

/**
 * Servlet implementation class UsersController
 */
@WebServlet({"/a/usuarios/listar", "/a/usuarios/deletar", "/a/usuarios/cadastrar"})
public class UsersController extends HttpServlet {
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().contains(Routes.READ)){ 
			List<User> users = userService.findAll();
	        List<UserDTO> usersDTO = new ArrayList<>();

	        for(User u : users){
	            usersDTO.add(UserMapper.toDTO(u));
	        }

	        request.setAttribute("users", usersDTO);
	        
	        String route = "/WEB-INF/view/admin/users.jsp";
			request.getRequestDispatcher(route).forward(request, response);	
		} else if(request.getServletPath().contains(Routes.DELETE)) {
			String id = request.getParameter("id");

	        List<ValidationError> errors = userService.paramValidation(id);
	        boolean hasError = errors != null;

	        if (hasError) {
	            throw new ParamException("Parâmetros incorretos!");
	        }

	        boolean isSuccess = userService.deleteUserAndRole(id);
	        String message = null;
	        if(isSuccess){
	            message = "Usuário removido com sucesso!";
	        }
	        else{
	            message = "Oppss! O usuário não pôde ser removido.";
	        }
	        String address = request.getContextPath() + "/a/usuarios/listar";
	        //request.setAttribute("flash.message", message);
	        //como a ação altera o estado do servidor, faz redirecionamento
	        response.sendRedirect(address);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
