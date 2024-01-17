package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.PermissionDto;
import com.slmtecnologia.model.entity.Permission;
import com.slmtecnologia.model.mapper.PermissionMapper;
import com.slmtecnologia.model.mapper.RoleMapper;
import com.slmtecnologia.repository.PermissionRepository;
import com.slmtecnologia.service.IPermissionService;
import com.slmtecnologia.service.IRoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {
    private  static final String RESOURCE_NOT_FOUND = "Permission not found!";

    private final PermissionRepository repository;
    private final IRoleService roleService;
    @Override
    public Page<PermissionDto> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(PermissionMapper::entityToDto);
    }

    @Override
    public PermissionDto findById(Long id) {
        return PermissionMapper.entityToDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }

    @Override
    @Transactional
    public PermissionDto create(PermissionDto dto) {
        if(Objects.isNull(dto)) throw new RequiredObjectIsNullException();

        Permission entity = PermissionMapper.dtoToEntity(dto);

        if(Objects.nonNull(dto.roleId())){
            var roleDto = roleService.findById(dto.roleId());
            entity.setRole(RoleMapper.dtoToEntity(roleDto));
        }

        return PermissionMapper.entityToDto(repository.save(entity));
    }

    @Override
    @Transactional
    public PermissionDto update(Long id, PermissionDto dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        var entityToSave = PermissionMapper.dtoToEntity(entity, dto);

        if(Objects.nonNull(dto.roleId())){
            var roleDto = roleService.findById(dto.roleId());
            entityToSave.setRole(RoleMapper.dtoToEntity(roleDto));
        }

        return PermissionMapper.entityToDto(repository.save(entityToSave));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        repository.deleteById(entity.getId());
    }

}
