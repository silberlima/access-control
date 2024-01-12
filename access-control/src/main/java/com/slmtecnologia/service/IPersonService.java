package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IPersonService {
    Page<PersonDto> findAll(Pageable pageable);
    PersonDto findById(Long id);
    PersonDto createPerson(PersonDto personDto) ;
    PersonDto updatePerson(Long id, PersonDto updatedPersonDto);
    void deletePerson(Long id) ;
    Page<PersonDto> findByName(String name, Pageable pageable);
}
