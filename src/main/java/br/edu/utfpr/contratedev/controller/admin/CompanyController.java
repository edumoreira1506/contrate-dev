package br.edu.utfpr.contratedev.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.CompanyDTO;
import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.CompanyMapper;
import br.edu.utfpr.contratedev.model.mapper.UserMapper;
import br.edu.utfpr.contratedev.model.service.CompanyService;
import br.edu.utfpr.contratedev.model.service.RoleService;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Routes;
import br.edu.utfpr.contratedev.util.Sha256Generator;

/**
 * Servlet implementation class CompanyController
 */
@WebServlet({"/a/empresas/cadastrar", "/a/empresas/listar"})
public class CompanyController extends HttpServlet {
	CompanyService companyService = new CompanyService();
	RoleService roleService = new RoleService();
	UserService userService = new UserService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().contains(Routes.CREATE)) {
			String address = "/WEB-INF/view/admin/register-company-form.jsp";
	        request.getRequestDispatcher(address).forward(request, response);	
		} else if (request.getServletPath().contains(Routes.READ)) {
			List<Company> companies = companyService.findAll();
	        List<CompanyDTO> companiesDTO = new ArrayList<>();

	        for(Company c : companies){
	            companiesDTO.add(CompanyMapper.toDTO(c));
	        }

	        request.setAttribute("companies", companiesDTO);
	        
	        String address = "/WEB-INF/view/admin/companies.jsp";
			request.getRequestDispatcher(address).forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyName = request.getParameter("company_name");
		String description = request.getParameter("description");
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String name = request.getParameter("name");
        char gender = request.getParameter("gender") == "M" ? 'm' : 'f';
        
        UserDTO userDTO = new UserDTO(name, "", "", email, "", password, gender, confirmPassword);
        CompanyDTO companyDTO = new CompanyDTO(userDTO, companyName, description); 
        List<ValidationError> errors = formValidation(companyDTO);

        boolean hasError = errors != null;

        if(hasError){
            sendError(request, response, errors);
            return;
        }

        if(request.getServletPath().contains(Routes.CREATE)){
            boolean isSuccess = persist(request, response, companyDTO);

            if(!isSuccess){
                String address = "/WEB-INF/view/admin/register-company-form.jsp";

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
	
	private boolean persist(HttpServletRequest request, HttpServletResponse response, CompanyDTO companyDTO) throws IOException, ServletException {
        Company company = CompanyMapper.toEntity(companyDTO);

        final String hashed = Sha256Generator.generate(company.getManager().getPassword());
        company.getManager().setPassword(hashed);

        Role roleDb = new Role(company.getManager().getEmail(), Constants.MANAGER);
        return userService.saveUserAndRole(company.getManager(), roleDb) && companyService.saveCompany(company);
    }
	
	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors) throws ServletException, IOException {
        String address = "/WEB-INF/view/admin/register-company-form.jsp";
        request.setAttribute("errors", errors);
        request.getRequestDispatcher(address).forward(request, response);
    }
	
	private List<ValidationError> formValidation(CompanyDTO companyDTO) {
        List<ValidationError> errors = new ArrayList<>();

        if (companyDTO.getManager().getName() == null || companyDTO.getManager().getName().isEmpty()) {
            errors.add(new ValidationError("name", "O campo nome do gerente é obrigatório."));
        }

        if (companyDTO.getManager().getEmail() == null || companyDTO.getManager().getEmail().isEmpty()) {
            errors.add(new ValidationError("email", "O campo email  do gerente é obrigatório."));
        }

        if (companyDTO.getManager().getPassword() == null || companyDTO.getManager().getPassword().isEmpty()) {
            errors.add(new ValidationError("password", "O campo senha do gerente é obrigatório."));
        }

        if (!companyDTO.getManager().getPassword().equals(companyDTO.getManager().getRepassword())) {
            errors.add(new ValidationError("password", "A confirmação da senha está diferente."));
        }
        
        if (companyDTO.getName() == null || companyDTO.getName().isEmpty()) {
        	errors.add(new ValidationError("company_name", "Nome da empresa é obrigatório"));
        }

        return (errors.isEmpty() ? null : errors);
    }

}
