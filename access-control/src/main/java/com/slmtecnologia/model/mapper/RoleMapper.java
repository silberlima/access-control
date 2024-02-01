package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.PermissionDto;
import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.model.dto.RoleDto;
import com.slmtecnologia.model.entity.Permission;
import com.slmtecnologia.model.entity.Role;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {

    private RoleMapper() { }

    public static Role dtoToEntity(RoleDto dto){
        if(Objects.isNull(dto)){ return null;}

        Set<Permission> permission = dto.permissions().stream().map(PermissionMapper::dtoToEntity).collect(Collectors.toSet());

        return Role.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .permissions(permission)
                .build();
    }

    public static Role dtoToEntity(Role entity, RoleDto dto){
        if(Objects.isNull(dto)){return null;}

        Set<Permission> permission = dto.permissions().stream().map(PermissionMapper::dtoToEntity).collect(Collectors.toSet());

        return Role.builder()
                .id(entity.getId())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static RoleDto entityToDto(Role entity){
        if(Objects.isNull(entity)){ return null;}

        List<PermissionDto> permissionDtos = entity.getPermissions().stream().map(PermissionMapper::entityToDto).toList();

        return new RoleDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                permissionDtos
        );
    }


}
