package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.ApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IApplicationService {

    Page<ApplicationDto> findAll(Pageable pageable);
    ApplicationDto findById(Long id);
    ApplicationDto create(ApplicationDto applicationDto) ;
    ApplicationDto update(Long id, ApplicationDto updatedApplicationDto);
    void delete(Long id) ;
    Page<ApplicationDto> findByName(String name, Pageable pageable);
}
