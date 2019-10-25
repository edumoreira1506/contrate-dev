package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.model.dao.RoleDAO;
import br.edu.utfpr.contratedev.model.dao.UserDAO;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.model.domain.User;

/**
 * Created by ronifabio on 01/05/2019.
 */
public class RoleService extends AbstractService<String, Role> {

    public RoleService() {
        dao = new RoleDAO();
    }
}