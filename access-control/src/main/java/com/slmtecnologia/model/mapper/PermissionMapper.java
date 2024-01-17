package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.PermissionDto;
import com.slmtecnologia.model.entity.Permission;

import java.util.Objects;

public class PermissionMapper {

    private PermissionMapper() { }

    public static Permission dtoToEntity(PermissionDto dto){
        if(Objects.isNull(dto)){ return null;}

        return Permission.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static Permission dtoToEntity(Permission entity, PermissionDto dto){
        if(Objects.isNull(dto)){return null;}

        return Permission.builder()
                .id(entity.getId())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static PermissionDto entityToDto(Permission entity){
        if(Objects.isNull(entity)){ return null;}

        return new PermissionDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                Objects.nonNull(entity.getRole()) ? entity.getRole().getId() : null
        );
    }


}
