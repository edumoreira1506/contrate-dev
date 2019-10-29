package br.edu.utfpr.contratedev.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class JobServlet
 */
@WebServlet("/u/vagas/editar")
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JobService jobService = new JobService();
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
		Long id = Long.parseLong(request.getParameter("id"));
		Job job = jobService.getById(id);
		
		String email = (String) request.getSession().getAttribute("username");
        User user = userService.getById(email);
        
        List<ValidationError> errors = validationDuplication(request, response, job, user);
        
        boolean hasError = errors != null;

        if(hasError){
            sendError(request, response, errors);
            return;
        }
        
        if(request.getServletPath().contains(Routes.UPDATE)){
        	Set<User> candidates = job.getCandidates();
        	candidates.add(user);
        	job.setCandidates(candidates);
        	
            boolean isSuccess = persistEdit(request, response, job);

            if(!isSuccess){
                String address = "/WEB-INF/view/user/jobs.jsp";

                errors = new ArrayList<>();
                errors.add(new ValidationError("", "Erro ao persistir os dados."));

                request.setAttribute("errors", errors);
                request.getRequestDispatcher(address).forward(request, response);
                return;
            }

            request.setAttribute("success", "Você se candidatou a vaga!");
            String route = request.getContextPath() + "/u";
            response.sendRedirect(route);
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<ValidationError> validationDuplication(HttpServletRequest request, HttpServletResponse response, Job job, User user) throws ServletException, IOException {
		List<ValidationError> errors = new ArrayList<>();

		if (job == null) {
			errors.add(new ValidationError("job","Job não pode ser nulo"));
		}
		
		if (job.getCandidates().contains(user)) {
			errors.add(new ValidationError("candidates", "Você já se candidatou"));
		}
		
		if (user == null) {
			errors.add(new ValidationError("user","Usuário inexistente"));
		}
		
        return (errors.isEmpty() ? null : errors);
	}
	
	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors) throws ServletException, IOException {
        String address = "/WEB-INF/view/user/jobs.jsp";
        request.setAttribute("errors", errors);
        request.getRequestDispatcher(address).forward(request, response);
    }
	
	private boolean persistEdit(HttpServletRequest request, HttpServletResponse response, Job job) throws IOException, ServletException {
        return jobService.update(job);
    }

}
