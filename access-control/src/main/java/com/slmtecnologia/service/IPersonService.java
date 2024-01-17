package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IPersonService {
    Page<PersonDto> findAll(Pageable pageable);
    PersonDto findById(Long id);
    PersonDto create(PersonDto personDto) ;
    PersonDto update(Long id, PersonDto updatedPersonDto);
    void delete(Long id) ;
    Page<PersonDto> findByName(String name, Pageable pageable);
}
