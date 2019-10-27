package br.edu.utfpr.contratedev.model.mapper;

import br.edu.utfpr.contratedev.dto.CompanyDTO;
import br.edu.utfpr.contratedev.model.domain.Company;

public class CompanyMapper {
	public static Company toEntity(CompanyDTO dto){
        Company entity = new Company(UserMapper.toEntity(dto.getManager()), dto.getName(), dto.getDescription());
        return entity;
    }

    public static CompanyDTO toDTO(Company entity){
    	CompanyDTO dto = new CompanyDTO (UserMapper.toDTO(entity.getManager()), entity.getName(), entity.getDescription());
        return dto;
    }
}
