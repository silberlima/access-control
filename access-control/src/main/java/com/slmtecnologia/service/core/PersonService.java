package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.model.entity.City;
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

    private  static final String RESOURCE_NOT_FOUND = "Person not found!";
    private  static final String CITY_NOT_FOUND = "City not found!";
    private final PersonRepository personRepository;
    private final CityRepository cityRepository;

    @Override
    public Page<PersonDto> findAll(Pageable pageable) {
        var page = personRepository.findAll(pageable);
        return page.map(PersonMapper::entityToDto);
    }
    @Transactional
    @Override
    public PersonDto findById(Long id) {
        return PersonMapper.entityToDto(personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }
    @Transactional
    @Override
    public PersonDto create(PersonDto dto) {
        if(Objects.isNull(dto)) throw new RequiredObjectIsNullException();

        var entity = PersonMapper.dtoToEntity(dto);

        if(Objects.nonNull(dto.cityId())){
            City city = cityRepository.findById(dto.cityId())
                    .orElseThrow(() -> new ResourceNotFoundException(CITY_NOT_FOUND));

            entity.setCity(city);
        }
        return PersonMapper.entityToDto(personRepository.save(entity));
    }


    @Transactional
    @Override
    public PersonDto update(Long id, PersonDto dto) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        var entityToSave = PersonMapper.dtoToEntity(entity, dto);
        entityToSave.setCity(entity.getCity());

        if((Objects.nonNull(entity.getCity()) && Objects.nonNull(dto.cityId()) && !entity.getCity().getId().equals(dto.cityId()))
            || (Objects.isNull(entity.getCity()) && Objects.nonNull(dto.cityId())) ) {

            City city = cityRepository.findById(dto.cityId())
                    .orElseThrow(() -> new ResourceNotFoundException(CITY_NOT_FOUND));

            entityToSave.setCity(city);
        }

        return PersonMapper.entityToDto(personRepository.save(entityToSave));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        personRepository.deleteById(entity.getId());
    }

    @Override
    public Page<PersonDto> findByName(String name, Pageable pageable){
        var page = personRepository.findByName(name, pageable);
        return page.map(PersonMapper::entityToDto);
    }

}
