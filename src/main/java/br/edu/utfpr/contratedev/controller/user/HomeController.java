package br.edu.utfpr.contratedev.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.utfpr.contratedev.dto.JobDTO;
import br.edu.utfpr.contratedev.model.domain.Job;
import br.edu.utfpr.contratedev.model.mapper.JobMapper;
import br.edu.utfpr.contratedev.model.service.JobService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(
		name = "HomeUserController", 
		urlPatterns = { 
				"/u", 
				"/u/home"
		})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JobService jobService = new JobService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Job> jobs = jobService.findAll();
        List<JobDTO> jobsDTO = new ArrayList<>();

        for(Job j : jobs){
            jobsDTO.add(JobMapper.toDTO(j));
        }

        request.setAttribute("jobs", jobsDTO);
        
        String address = "/WEB-INF/view/user/jobs.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
