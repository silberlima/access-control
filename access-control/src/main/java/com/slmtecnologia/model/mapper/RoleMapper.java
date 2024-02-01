package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.RoleDto;
import com.slmtecnologia.model.entity.Role;

import java.util.Objects;

public class RoleMapper {

    private RoleMapper() { }

    public static Role dtoToEntity(RoleDto dto){
        if(Objects.isNull(dto)){ return null;}

        return Role.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static Role dtoToEntity(Role entity, RoleDto dto){
        if(Objects.isNull(dto)){return null;}

        return Role.builder()
                .id(entity.getId())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static RoleDto entityToDto(Role entity){
        if(Objects.isNull(entity)){ return null;}

        return new RoleDto(entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }


}
