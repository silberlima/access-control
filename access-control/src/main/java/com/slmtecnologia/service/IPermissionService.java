package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.PermissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPermissionService {

    Page<PermissionDto> findAll(Pageable pageable);
    PermissionDto findById(Long id);
    PermissionDto create(PermissionDto dto) ;
    PermissionDto update(Long id, PermissionDto updatedDto);
    void delete(Long id) ;
}
