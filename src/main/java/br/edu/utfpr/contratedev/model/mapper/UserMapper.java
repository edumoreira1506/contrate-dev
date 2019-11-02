package br.edu.utfpr.contratedev.model.mapper;

import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.model.domain.User;

public class UserMapper {
    public static User toEntity(UserDTO dto){
        User entity = new User(dto.getName(), dto.getLanguage(), dto.getDescription(), dto.getEmail(), dto.getCellphone(), dto.getPassword(), dto.getGender(), dto.getGithub());
        return entity;
    }

    public static UserDTO toDTO(User entity){
    	UserDTO dto = new UserDTO(entity.getName(), entity.getLanguage(), entity.getDescription(), entity.getEmail(), entity.getCellphone(), entity.getPassword(), entity.getGender(), entity.getGithub());
        return dto;
    }
}
