package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {

    Page<RoleDto> findAll(Pageable pageable);
    RoleDto findById(Long id);
    RoleDto create(RoleDto dto) ;
    RoleDto update(Long id, RoleDto updatedDto);
    void delete(Long id) ;
}
