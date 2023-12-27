package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.PersonDto;
import com.slmtecnologia.entity.Person;

public class PersonMapper {

    private PersonMapper() { }

    public static Person dtoToEntity(PersonDto dto){
        return new Person(dto.id(),
                dto.name(),
                dto.cpf(),
                dto.email());
    }

    public static PersonDto entityToDto(Person entity){
        return new PersonDto(entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail());
    }

    public static Person dtoToEntity(Long id, PersonDto dto){
        return new Person(id,
                dto.name(),
                dto.cpf(),
                dto.email());
    }

}
