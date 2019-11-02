package br.edu.utfpr.contratedev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.domain.Job;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.JobMapper;
import br.edu.utfpr.contratedev.model.mapper.UserMapper;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Sha256Generator;

/**
 * Servlet implementation class EditController
 */
@WebServlet({"/g/editar-perfil", "/a/editar-perfil", "/u/editar-perfil"})
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("username");
        User user = userService.getById(email);
        UserDTO userDTO = UserMapper.toDTO(user);
        
        request.setAttribute("user", userDTO);
        
        String address = "/WEB-INF/view/edit-perfil.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String language = request.getParameter("language");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
		String cellphone = request.getParameter("cellphone");
		String id = request.getParameter("id");
		String github = request.getParameter("github");
		
		User user = userService.getById(id);
		
		user.setCellphone(cellphone);
		user.setEmail(email);
		user.setDescription(description);
		user.setName(name);
		user.setLanguage(language);
		user.setGithub(github);
		
		List<ValidationError> errors = formValidation(user);

		boolean hasError = errors != null;

		if (hasError) {
			sendError(request, response, errors);
			return;
		}
		
		boolean isSuccess = persist(request, response, user);

		if (!isSuccess) {
			String address = "/WEB-INF/view/edit-perfil.jsp";

			errors = new ArrayList<>();
			errors.add(new ValidationError("", "Erro ao persistir os dados."));

			request.setAttribute("errors", errors);
			request.getRequestDispatcher(address).forward(request, response);
			return;
		}

		String address = request.getContextPath() + "/";
		response.sendRedirect(address);
	}
	
	private boolean persist(HttpServletRequest request, HttpServletResponse response, User user)
			throws IOException, ServletException {
        return userService.update(user);
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors)
			throws ServletException, IOException {
		String address = "/WEB-INF/view/edit-perfil.jsp";
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(address).forward(request, response);
	}

	private List<ValidationError> formValidation(User user) {
		List<ValidationError> errors = new ArrayList<>();
		
		if (user.getGithub() == null || user.getGithub().isEmpty()) {
			errors.add(new ValidationError("github", "O campo github é obrigatório"));
		}

		if (user.getName() == null || user.getName().isEmpty()) {
			errors.add(new ValidationError("name", "O campo nome é obrigatório."));
		}

		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			errors.add(new ValidationError("email", "O campo email é obrigatório."));
		}

		return (errors.isEmpty() ? null : errors);
	}

}
