package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.UserDto;
import com.slmtecnologia.model.entity.Role;
import com.slmtecnologia.model.entity.User;

import java.util.List;

public class UserMapper {

    private UserMapper(){}

    public static User dtoToEntity(UserDto dto){
        return User.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
    }

    public static UserDto entityToDto(User entity){
        List<Long> ids = entity.getRoles()
                .stream()
                .map(Role::getId)
                .toList();

        return new UserDto(
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    ids
                );
    }
}
