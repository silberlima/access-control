package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.model.entity.City;
import com.slmtecnologia.model.entity.Person;
import com.slmtecnologia.model.mapper.PersonMapper;
import com.slmtecnologia.repository.CityRepository;
import com.slmtecnologia.repository.PersonRepository;
import com.slmtecnologia.service.IPersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private  static final String RESOURCE_NOT_FOUND = "Recurso não encontrado!";
    private  static final String CITY_NOT_FOUND = "Cidade não encontrada!";
    private final PersonRepository personRepository;
    private final CityRepository cityRepository;

    @Override
    public Page<PersonDto> findAll(Pageable pageable) {
        var personPage = personRepository.findAll(pageable);
        return personPage.map(PersonMapper::entityToDto);
    }
    @Transactional
    @Override
    public PersonDto findById(Long id) {
        return PersonMapper.entityToDto(personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }
    @Transactional
    @Override
    public PersonDto createPerson(PersonDto personDto) {
        if(Objects.isNull(personDto)) throw new RequiredObjectIsNullException();

        Person person = PersonMapper.dtoToEntity(personDto);

        if(Objects.nonNull(personDto.cityId())){
            City city = cityRepository.findById(personDto.cityId())
                    .orElseThrow(() -> new ResourceNotFoundException(CITY_NOT_FOUND));

            person.setCity(city);
        }
        return PersonMapper.entityToDto(personRepository.save(person));
    }


    @Transactional
    @Override
    public PersonDto updatePerson(Long id, PersonDto updatedPersonDto) {
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

        return PersonMapper.entityToDto(personRepository.save(personToSave));
    }

    @Transactional
    @Override
    public void deletePerson(Long id) {
        var personEntity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        personRepository.deleteById(personEntity.getId());
    }

    @Override
    public Page<PersonDto> findByName(String name, Pageable pageable){
        var personPage = personRepository.findByName(name, pageable);
        return personPage.map(PersonMapper::entityToDto);
    }

}
