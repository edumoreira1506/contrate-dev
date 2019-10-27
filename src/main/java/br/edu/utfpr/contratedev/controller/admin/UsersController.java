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
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Routes;
import br.edu.utfpr.contratedev.util.Sha256Generator;

/**
 * Servlet implementation class UsersController
 */
@WebServlet({"/a/usuarios/listar", "/a/usuarios/remover", "/a/usuarios/cadastrar"})
public class UsersController extends HttpServlet {
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	
	private static final long serialVersionUID = 1L;
       
    public UsersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(request.getServletPath().contains(Routes.CREATE)) {
			String address = "/WEB-INF/view/admin/register-user-form.jsp";
            request.getRequestDispatcher(address).forward(request, response);
		}else if(request.getServletPath().contains(Routes.READ)) { 
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
	        if(isSuccess) {
	        	message = "Usuário removido com sucesso!";
	        } else {
	            message = "Oppss! O usuário não pôde ser removido.";
	        }
	        String address = request.getContextPath() + "/a/usuarios/listar";
	        request.setAttribute("flash.message", message);
	        response.sendRedirect(address);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String language = request.getParameter("language");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String cellphone = request.getParameter("cellphone");
        char gender = request.getParameter("gender") == "M" ? 'm' : 'f';
        String role = request.getParameter("role");
        
        UserDTO userDTO = new UserDTO(name, language, description, email, cellphone, password, gender, confirmPassword);
        List<ValidationError> errors = formValidation(userDTO, role);

        boolean hasError = errors != null;

        if(hasError){
            sendError(request, response, errors);
            return;
        }

        if(request.getServletPath().contains(Routes.CREATE)){
            boolean isSuccess = persist(request, response, userDTO, role);

            if(!isSuccess){
                String address = "/WEB-INF/view/admin/register-user-form.jsp";

                errors = new ArrayList<>();
                errors.add(new ValidationError("", "Erro ao persistir os dados."));

                request.setAttribute("errors", errors);
                request.getRequestDispatcher(address).forward(request, response);
                return;
            }

            String route = request.getContextPath() + "/a/usuarios/listar";
            response.sendRedirect(route);
        }
	}
	
	private boolean persist(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO, String role) throws IOException, ServletException {
        User user = UserMapper.toEntity(userDTO);

        final String hashed = Sha256Generator.generate(user.getPassword());
        user.setPassword(hashed);

        Role roleDb = new Role(userDTO.getEmail(), role);
        return userService.saveUserAndRole(user, roleDb);
    }
	
	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors) throws ServletException, IOException {
        String address = "/WEB-INF/view/admin/register-user-form.jsp";
        request.setAttribute("errors", errors);
        request.getRequestDispatcher(address).forward(request, response);
    }
	
	private List<ValidationError> formValidation(UserDTO userDTO, String role) {
        List<ValidationError> errors = new ArrayList<>();

        if (role.isEmpty()) {
             errors.add(new ValidationError("role", "Tipo deve ser admin ou comum."));
        }
        
        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
            errors.add(new ValidationError("name", "O campo nome é obrigatório."));
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.add(new ValidationError("email", "O campo email é obrigatório."));
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            errors.add(new ValidationError("password", "O campo senha é obrigatório."));
        }

        if (!userDTO.getPassword().equals(userDTO.getRepassword())) {
            errors.add(new ValidationError("password", "A confirmação da senha está diferente."));
        }

        return (errors.isEmpty() ? null : errors);
    }

}
