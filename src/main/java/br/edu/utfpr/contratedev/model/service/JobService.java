package br.edu.utfpr.contratedev.model.service;

import java.util.List;

import br.edu.utfpr.contratedev.model.dao.JobDAO;
import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.Job;

public class JobService extends AbstractService<Long, Job> {

    public JobService() {
        dao = new JobDAO();
    }
    
    public List<Job> findByCompany(Company company) {
    	List<Job> entities = null;
        entities = dao.listByForeignOrObjectProperty("company", company);
        return entities;
    }
}