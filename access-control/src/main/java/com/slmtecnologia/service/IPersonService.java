package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.PersonDetailDto;
import com.slmtecnologia.model.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    List<PersonDto> getAllPeople();
    PersonDetailDto getPersonById(Long id);
    PersonDetailDto createPerson(PersonDto personDto) ;
    PersonDetailDto updatePerson(Long id, PersonDto updatedPersonDto);
    void deletePerson(Long id) ;
}
