package com.slmtecnologia.service.core;

import com.slmtecnologia.model.dto.PersonDetailDto;
import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.controller.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.controller.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.mapper.PersonMapper;
import com.slmtecnologia.model.entity.City;
import com.slmtecnologia.model.entity.Person;
import com.slmtecnologia.repository.CityRepository;
import com.slmtecnologia.repository.PersonRepository;
import com.slmtecnologia.service.IPersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService implements IPersonService {

    private  static final String RESOURCE_NOT_FOUND = "Recurso não encontrado!";
    private  static final String CITY_NOT_FOUND = "Cidade não encontrada!";
    private final PersonRepository personRepository;
    private final CityRepository cityRepository;
    @Autowired
    public PersonService(PersonRepository personRepository, CityRepository cityRepository){
        this.personRepository = personRepository;
        this.cityRepository = cityRepository;
    }
    @Override
    public List<PersonDto> getAllPeople() {
        return personRepository.findAll()
                .stream()
                .map(PersonMapper::entityToDto)
                .toList();
    }
    @Transactional
    @Override
    public PersonDetailDto getPersonById(Long id) {
        return PersonMapper.entityToDetailDto(personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }
    @Transactional
    @Override
    public PersonDetailDto createPerson(PersonDto personDto) {
        if(Objects.isNull(personDto)) throw new RequiredObjectIsNullException();

        Person person = PersonMapper.dtoToEntity(personDto);

        if(Objects.nonNull(personDto.cityId())){
            City city = cityRepository.findById(personDto.cityId())
                    .orElseThrow(() -> new ResourceNotFoundException(CITY_NOT_FOUND));

            person.setCity(city);
        }
        return PersonMapper.entityToDetailDto(personRepository.save(person));
    }

    @Transactional
    @Override
    public PersonDetailDto updatePerson(Long id, PersonDto updatedPersonDto) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        var personToSave = PersonMapper.dtoToEntity(entity, updatedPersonDto);
        personToSave.setCity(entity.getCity());

        if((Objects.nonNull(entity.getCity()) && Objects.nonNull(updatedPersonDto.cityId()) && !entity.getCity().getId().equals(updatedPersonDto.cityId()))
            || (Objects.isNull(entity.getCity()) && Objects.nonNull(updatedPersonDto.cityId())) ) {

            City city = cityRepository.findById(updatedPersonDto.cityId())
                    .orElseThrow(() -> new ResourceNotFoundException(CITY_NOT_FOUND));

            personToSave.setCity(city);
        }

        return PersonMapper.entityToDetailDto(personRepository.save(personToSave));
    }

    @Transactional
    @Override
    public void deletePerson(Long id) {
        var personEntity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        personRepository.deleteById(personEntity.getId());
    }

}
