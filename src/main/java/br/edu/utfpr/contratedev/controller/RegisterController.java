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
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.UserMapper;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Routes;
import br.edu.utfpr.contratedev.util.Sha256Generator;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/registrar-se")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.isUserInRole(Constants.ADMIN)) {
			response.sendRedirect("a/home");
		} else if (request.isUserInRole(Constants.MANAGER)) {
			response.sendRedirect("g/home");
		} else if (request.isUserInRole(Constants.USER)) {
			response.sendRedirect("u/home");
		} else {
			String route = "/WEB-INF/view/register.jsp";
			request.getRequestDispatcher(route).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String language = request.getParameter("language");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String cellphone = request.getParameter("cellphone");
		char gender = request.getParameter("gender") == "M" ? 'm' : 'f';
		String github = request.getParameter("github");

		UserDTO userDTO = new UserDTO(name, language, description, email, cellphone, password, gender, github, confirmPassword);
		List<ValidationError> errors = formValidation(userDTO);

		boolean hasError = errors != null;

		if (hasError) {
			sendError(request, response, errors);
			return;
		}

		boolean isSuccess = persist(request, response, userDTO);

		if (!isSuccess) {
			String address = "/WEB-INF/view/register.jsp";

			errors = new ArrayList<>();
			errors.add(new ValidationError("", "Erro ao persistir os dados."));

			request.setAttribute("errors", errors);
			request.getRequestDispatcher(address).forward(request, response);
			return;
		}

		String address = request.getContextPath() + "/login";
		response.sendRedirect(address);
	}

	private boolean persist(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO)
			throws IOException, ServletException {
		User user = UserMapper.toEntity(userDTO);

		final String hashed = Sha256Generator.generate(user.getPassword());
		user.setPassword(hashed);
		
		Long amountUsers = userService.count();
		String role = amountUsers == 0 ? Constants.ADMIN : Constants.USER;
		Role roleDb = new Role(userDTO.getEmail(), role);
		return userService.saveUserAndRole(user, roleDb);
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors)
			throws ServletException, IOException {
		String address = "/WEB-INF/view/register.jsp";
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(address).forward(request, response);
	}

	private List<ValidationError> formValidation(UserDTO userDTO) {
		List<ValidationError> errors = new ArrayList<>();

		if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
			errors.add(new ValidationError("name", "O campo nome é obrigatório."));
		}
		
		if (userDTO.getGithub() == null || userDTO.getGithub().isEmpty()) {
			errors.add(new ValidationError("github", "O campo github é obrigatório."));
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
