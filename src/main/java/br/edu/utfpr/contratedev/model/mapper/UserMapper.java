package br.edu.utfpr.contratedev.model.mapper;

import br.edu.utfpr.contratedev.dto.UserDTO;
import br.edu.utfpr.contratedev.model.domain.User;

public class UserMapper {
    public static User toEntity(UserDTO dto){
        User entity = new User();
        return entity;
    }

    public static UserDTO toDTO(User entity){
    	UserDTO dto = new UserDTO();
        return dto;
    }
}
