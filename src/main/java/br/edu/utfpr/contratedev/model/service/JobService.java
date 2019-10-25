package br.edu.utfpr.contratedev.model.service;

import br.edu.utfpr.contratedev.model.dao.JobDAO;
import br.edu.utfpr.contratedev.model.domain.Job;

/**
 * Created by ronifabio on 01/05/2019.
 */
public class JobService extends AbstractService<Long, Job> {

    public JobService() {
        dao = new JobDAO();
    }
}