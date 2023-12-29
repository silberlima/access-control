package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.PersonDto;
import com.slmtecnologia.entity.Person;

import java.util.Objects;

public class PersonMapper {

    private PersonMapper() { }

    public static Person dtoToEntity(PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        return new Person(dto.id(),
                dto.name(),
                dto.cpf(),
                dto.email());
    }

    public static PersonDto entityToDto(Person entity){
        if(Objects.isNull(entity)){ return null;}

        return new PersonDto(entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail());
    }

    public static Person dtoToEntity(Long id, PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        return new Person(id,
                dto.name(),
                dto.cpf(),
                dto.email());
    }

}
