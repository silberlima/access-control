package com.slmtecnologia.service.core;

import com.slmtecnologia.controller.dto.PersonDto;
import com.slmtecnologia.controller.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.controller.exceptions.ResourceNotFoundException;
import com.slmtecnologia.controller.mapper.PersonMapper;
import com.slmtecnologia.entity.Person;
import com.slmtecnologia.repository.PersonRepository;
import com.slmtecnologia.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService implements IPersonService {

    private  static final String RESOURCE_NOT_FOUND = "Recurso não encontrado!";
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    @Override
    public List<PersonDto> getAllPeople() {
        return personRepository.findAll()
                .stream()
                .map(PersonMapper::entityToDto)
                .toList();
    }
    @Override
    public PersonDto getPersonById(Long id) {
        return PersonMapper.entityToDto(personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }
    @Override
    public PersonDto createPerson(PersonDto personDto) {
        if(Objects.isNull(personDto)) throw new RequiredObjectIsNullException();
        Person person = PersonMapper.dtoToEntity(personDto);
        return PersonMapper.entityToDto(personRepository.save(person));
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto updatedPersonDto) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        return PersonMapper.entityToDto(personRepository.save(PersonMapper.dtoToEntity(entity.getId(), updatedPersonDto)));
    }

    @Override
    public void deletePerson(Long id) {
        var personEntity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        personRepository.deleteById(personEntity.getId());
    }

}
