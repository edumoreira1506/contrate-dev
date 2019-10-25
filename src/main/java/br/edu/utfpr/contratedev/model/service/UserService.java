package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.model.dao.RoleDAO;
import br.edu.utfpr.contratedev.model.dao.UserDAO;
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

        boolean isSuccess = true;
        try {
            User user = dao.getById(id);
            Role role = roleDAO.getById(id);

            JPAUtil.beginTransaction();
            dao.delete(user);
            roleDAO.delete(role);
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
}
