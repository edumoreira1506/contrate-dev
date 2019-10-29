package br.edu.utfpr.contratedev.model.mapper;

import br.edu.utfpr.contratedev.dto.JobDTO;
import br.edu.utfpr.contratedev.model.domain.Job;

public class JobMapper {
	public static Job toEntity(JobDTO dto){
        Job entity = new Job(dto.getName(), dto.getSalary(), dto.getDescription(), dto.getCandidates(), dto.getCompany());
        return entity;
    }

    public static JobDTO toDTO(Job entity){
    	JobDTO dto = new JobDTO(entity.getName(), entity.getSalary(), entity.getDescription(), entity.getCandidates(), entity.getCompany());
        return dto;
    }
}
