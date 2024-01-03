package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.PersonDetailDto;
import com.slmtecnologia.controller.dto.PersonDto;
import com.slmtecnologia.repository.entity.Person;

import java.util.Objects;

public class PersonMapper {

    private PersonMapper() { }

    public static Person dtoToEntity(PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        var person = PersonMapper.toEntityWithoutId(dto);
        person.setId(dto.id());

        return person;
    }

    public static Person dtoToEntity(Person entity, PersonDto dto){
        if(Objects.isNull(dto)){return null;}

        var person = PersonMapper.toEntityWithoutId(dto);
        person.setId(entity.getId());

        return person;
    }

    public static PersonDetailDto entityToDetailDto(Person entity){
        if(Objects.isNull(entity)){ return null;}

        return new PersonDetailDto(entity.getId(),
                entity.getName(),
                entity.getSocialName(),
                entity.getFatherName(),
                entity.getMotherName(),
                entity.getCpf(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getStreet(),
                Objects.nonNull(entity.getCity()) ? CityMapper.entityToDto(entity.getCity()) : null,
                entity.getZipCode()
        );
    }

    public static PersonDto entityToDto(Person entity){
        if(Objects.isNull(entity)){ return null;}

        return new PersonDto(entity.getId(),
                entity.getName(),
                entity.getSocialName(),
                entity.getFatherName(),
                entity.getMotherName(),
                entity.getCpf(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getStreet(),
                Objects.nonNull(entity.getCity()) ? entity.getCity().getId() : null,
                entity.getZipCode()
        );
    }

    private static Person toEntityWithoutId(PersonDto dto){
        Person person = new Person();
        person.setName(dto.name());
        person.setSocialName(dto.socialName());
        person.setFatherName(dto.fatherName());
        person.setMotherName(dto.motherName());
        person.setCpf(dto.cpf());
        person.setBirthDate(dto.birthDate());
        person.setEmail(dto.email());
        person.setStreet(dto.street());
        person.setZipCode(dto.zipCode());
        return person;
    }


}
