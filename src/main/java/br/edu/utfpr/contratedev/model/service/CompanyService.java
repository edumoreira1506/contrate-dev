package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.model.dao.CompanyDAO;
import br.edu.utfpr.contratedev.model.domain.Company;

/**
 * Created by ronifabio on 01/05/2019.
 */
public class CompanyService extends AbstractService<Long, Company> {

    public CompanyService() {
        dao = new CompanyDAO();
    }
}