package com.slmtecnologia.service;

import com.slmtecnologia.controller.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    List<PersonDto> getAllPeople();
    PersonDto getPersonById(Long id);
    PersonDto createPerson(PersonDto personDto) ;
    PersonDto updatePerson(Long id, PersonDto updatedPersonDto);
    void deletePerson(Long id) ;
}
