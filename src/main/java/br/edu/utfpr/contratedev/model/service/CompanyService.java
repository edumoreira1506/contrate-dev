package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.model.dao.CompanyDAO;
import br.edu.utfpr.contratedev.model.dao.RoleDAO;
import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.Role;
import br.edu.utfpr.contratedev.util.JPAUtil;

public class CompanyService extends AbstractService<Long, Company> {

    public CompanyService() {
        dao = new CompanyDAO();
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
}