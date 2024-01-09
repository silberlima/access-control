package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.PersonDetailDto;
import com.slmtecnologia.model.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    Page<PersonDto> findAll(Pageable pageable);
    PersonDetailDto findById(Long id);
    PersonDetailDto createPerson(PersonDto personDto) ;
    PersonDetailDto updatePerson(Long id, PersonDto updatedPersonDto);
    void deletePerson(Long id) ;
    Page<PersonDto> findByName(String name, Pageable pageable);
}
