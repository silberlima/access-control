package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.model.entity.Person;

import java.util.Objects;

public class PersonMapper {

    private PersonMapper() { }

    public static Person dtoToEntity(PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        return Person.builder()
                .id(dto.id())
                .name(dto.name())
                .socialName(dto.socialName())
                .fatherName(dto.fatherName())
                .motherName(dto.motherName())
                .cpf(dto.cpf())
                .birthDate(dto.birthDate())
                .email(dto.email())
                .street(dto.street())
                .zipCode(dto.zipCode())
                .build();
    }

    public static Person dtoToEntity(Person entity, PersonDto dto){
        if(Objects.isNull(dto)){return null;}

        return Person.builder()
                .id(entity.getId())
                .name(dto.name())
                .socialName(dto.socialName())
                .fatherName(dto.fatherName())
                .motherName(dto.motherName())
                .cpf(dto.cpf())
                .birthDate(dto.birthDate())
                .email(dto.email())
                .street(dto.street())
                .zipCode(dto.zipCode())
                .build();
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


}
