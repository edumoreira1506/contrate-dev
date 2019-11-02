package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.error.ValidationError;
import br.edu.utfpr.contratedev.model.dao.JobDAO;
import br.edu.utfpr.contratedev.model.dao.RoleDAO;
import br.edu.utfpr.contratedev.model.dao.UserDAO;
import br.edu.utfpr.contratedev.model.domain.Job;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;
import br.edu.utfpr.contratedev.util.JPAUtil;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractService<String, User> {

    public UserService() {
        dao = new UserDAO();
    }

    public boolean saveUserAndRole(User user, Role role){
        RoleDAO roleDAO = new RoleDAO();

        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.save(user);
            roleDAO.save(role);
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

    public boolean deleteUserAndRole(String id){
        RoleDAO roleDAO= new RoleDAO();
        JobDAO jobDAO = new JobDAO();

        boolean isSuccess = true;
        try {
            User user = dao.getById(id);
            Role role = roleDAO.getById(id);
            List<Job> jobs = jobDAO.findAll();
            JPAUtil.beginTransaction();

            for(Job job : jobs) {
            	if (job.getCandidates().contains(user)) {
            		job.removeCandidate(user);
                	jobDAO.update(job);	
            	}
            }
            
            dao.delete(user);
            roleDAO.delete(role);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        }
        
        return isSuccess;
    }
    
    public List<ValidationError> paramValidation(String id) {
        List<ValidationError> errors = new ArrayList<>();

        if (id == null || id.isEmpty()) {
            errors.add(new ValidationError("id", "O identificador do item é obrigatório."));
        }

        User user = getById(id);
        if (user == null) {
            errors.add(new ValidationError("id", "O item não foi encontrado."));
        }
        return (errors.isEmpty() ? null : errors);
    }
}
