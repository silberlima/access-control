package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.RoleDto;
import com.slmtecnologia.model.mapper.ApplicationMapper;
import com.slmtecnologia.model.mapper.RoleMapper;
import com.slmtecnologia.repository.RoleRepository;
import com.slmtecnologia.service.IRoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private  static final String RESOURCE_NOT_FOUND = "Role not found!";

    private final RoleRepository repository;

    private final ApplicationService applicationService;
    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(RoleMapper::entityToDto);
    }

    @Override
    public RoleDto findById(Long id) {
        return RoleMapper.entityToDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }

    @Override
    @Transactional
    public RoleDto create(RoleDto dto) {
        if(Objects.isNull(dto)) throw new RequiredObjectIsNullException();

        var entity = RoleMapper.dtoToEntity(dto);

        return RoleMapper.entityToDto(repository.save(entity));
    }

    @Override
    @Transactional
    public RoleDto update(Long id, RoleDto dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        var entityToSave = RoleMapper.dtoToEntity(entity, dto);

        return RoleMapper.entityToDto(repository.save(entityToSave));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        repository.deleteById(entity.getId());
    }

}
