package br.edu.utfpr.contratedev.model.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.dao.CompanyDAO;
import br.edu.utfpr.contratedev.model.dao.JobDAO;
import br.edu.utfpr.contratedev.model.dao.RoleDAO;
import br.edu.utfpr.contratedev.model.dao.UserDAO;
import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.Job;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.util.JPAUtil;

public class CompanyService extends AbstractService<Long, Company> {
    public CompanyService() {
        dao = new CompanyDAO();
    }
    
    public boolean deleteCompany(Long id) {
    	RoleDAO roleDAO = new RoleDAO();
    	UserDAO userDAO = new UserDAO();
    	JobDAO jobDAO = new JobDAO();
    	
    	boolean isSuccess = true;
        try {
        	Company company = dao.getById(id);
        	User user = userDAO.getById(company.getManager().getEmail());
            Role role = roleDAO.getById(company.getManager().getEmail());
        	
            List<Job> jobs = jobDAO.findAll();
            
            for(Job job : jobs) {
            	if (job.getCompany() == company) {
            		jobDAO.delete(job);
            	}
            }
            
            JPAUtil.beginTransaction();
            userDAO.delete(user);
            roleDAO.delete(role);
            dao.delete(company);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        }
        
        return isSuccess;
    }
    
    public boolean saveCompany(Company company) {
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.save(company);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        } finally {
        	JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }
    
    public List<ValidationError> paramValidation(Long id) {
        List<ValidationError> errors = new ArrayList<>();

        if (id == null || id <= 0) {
            errors.add(new ValidationError("id", "O identificador do item é obrigatório."));
        }

        Company company = getById(id);
        if (company == null) {
            errors.add(new ValidationError("id", "O item não foi encontrado."));
        }
        return (errors.isEmpty() ? null : errors);
    }
    
    public Company getByForeignOrObjectProperty(String property, Object value) {
    	Company company = dao.getByForeignOrObjectProperty(property, value);
    	return company;
    }
}