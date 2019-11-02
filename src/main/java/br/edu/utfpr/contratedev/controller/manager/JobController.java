package br.edu.utfpr.contratedev.controller.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.CompanyDTO;
import br.edu.utfpr.contratedev.dto.JobDTO;
import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.Job;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.model.mapper.JobMapper;
import br.edu.utfpr.contratedev.model.service.CompanyService;
import br.edu.utfpr.contratedev.model.service.JobService;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Routes;

/**
 * Servlet implementation class JobController
 */
@WebServlet({"/g/vagas/cadastrar", "/g/vagas/listar", "/g/vagas/editar", "/g/vagas/visualizar"})
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JobService jobService = new JobService();
	CompanyService companyService = new CompanyService();
	UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getServletPath().contains(Routes.CREATE)) {
			String address = "/WEB-INF/view/manager/register-job.jsp";
	        request.getRequestDispatcher(address).forward(request, response);			
		} else if(request.getServletPath().contains(Routes.READ)) {
			String email = (String) request.getSession().getAttribute("username");
	        User admin = userService.getById(email);
	        Company company = companyService.getByForeignOrObjectProperty("manager", admin);
	        
			List<Job> jobs = jobService.findByCompany(company);
	        List<JobDTO> jobsDTO = new ArrayList<>();

	        for(Job j : jobs){
	            jobsDTO.add(JobMapper.toDTO(j));
	        }

	        request.setAttribute("jobs", jobsDTO);
	        
	        String address = "/WEB-INF/view/manager/jobs.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		} else if(request.getServletPath().contains(Routes.UPDATE)) {
			Long id = Long.parseLong(request.getParameter("id"));
			
			Job job = jobService.getById(id);
			JobDTO jobDTO = JobMapper.toDTO(job);
			
			request.setAttribute("job", jobDTO);
			
			String address = "/WEB-INF/view/manager/edit-job.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		} else if(request.getServletPath().contains(Routes.READ_ONE)) {
			Long id = Long.parseLong(request.getParameter("id"));
			
			Job job = jobService.getById(id);
			JobDTO jobDTO = JobMapper.toDTO(job);
			
			request.setAttribute("job", jobDTO);
			
			String address = "/WEB-INF/view/manager/job.jsp";
			request.getRequestDispatcher(address).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
        String salary = request.getParameter("salary");
        
        String email = (String) request.getSession().getAttribute("username");
        User admin = userService.getById(email);
        Company company = companyService.getByForeignOrObjectProperty("manager", admin);
        Set<User> candidates = new HashSet<User>();
        
        JobDTO jobDTO = new JobDTO(name, salary, description, candidates, company);
        List<ValidationError> errors = formValidation(jobDTO);

        boolean hasError = errors != null;

        if(hasError){
            sendError(request, response, errors);
            return;
        }

        if(request.getServletPath().contains(Routes.CREATE)){
            boolean isSuccess = persist(request, response, jobDTO);

            if(!isSuccess){
                String address = "/WEB-INF/view/manager/register-job.jsp";

                errors = new ArrayList<>();
                errors.add(new ValidationError("", "Erro ao persistir os dados."));

                request.setAttribute("errors", errors);
                request.getRequestDispatcher(address).forward(request, response);
                return;
            }

            String route = request.getContextPath() + "/g/vagas/listar";
            response.sendRedirect(route);
        } else if(request.getServletPath().contains(Routes.UPDATE)) {
        	Long id = Long.parseLong(request.getParameter("id"));
        	jobDTO.setId(id);
        	
        	boolean isSuccess = persistEdit(request, response, jobDTO);
        	
        	if(!isSuccess){
                String address = "/WEB-INF/view/user/jobs.jsp";

                errors = new ArrayList<>();
                errors.add(new ValidationError("", "Erro ao persistir os dados."));

                request.setAttribute("errors", errors);
                request.getRequestDispatcher(address).forward(request, response);
                return;
            }
        	
        	String route = request.getContextPath() + "/g/vagas/listar";
            response.sendRedirect(route);
        }
	}
	
	private boolean persistEdit(HttpServletRequest request, HttpServletResponse response, JobDTO jobDTO) throws IOException, ServletException {
        Job job = JobMapper.toEntity(jobDTO);
        return jobService.update(job);
    }
	
	private boolean persist(HttpServletRequest request, HttpServletResponse response, JobDTO jobDTO) throws IOException, ServletException {
        Job job = JobMapper.toEntity(jobDTO);
        return jobService.save(job);
    }
	
	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors) throws ServletException, IOException {
		String address = "/WEB-INF/view/user/jobs.jsp";
        request.setAttribute("errors", errors);
        request.getRequestDispatcher(address).forward(request, response);
    }
	
	private List<ValidationError> formValidation(JobDTO jobDTO) {
        List<ValidationError> errors = new ArrayList<>();

        if (jobDTO.getSalary() == null) {
        	errors.add(new ValidationError("salary", "Salário não pode ser negativo"));
        }
        
        if (jobDTO.getName() == null || jobDTO.getName().isEmpty()) {
        	errors.add(new ValidationError("name", "Nome do cargo não pode ser vazio"));
        }
        
        if (jobDTO.getCompany() == null) {
        	errors.add(new ValidationError("company", "Empresa não pode estar vazio"));
        }

        return (errors.isEmpty() ? null : errors);
    }

}
