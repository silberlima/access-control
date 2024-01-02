package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.PersonDetailDto;
import com.slmtecnologia.controller.dto.PersonDto;
import com.slmtecnologia.entity.Person;

import java.util.Objects;

public class PersonMapper {

    private PersonMapper() { }a

    public static Person dtoToEntity(PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        return new Person(dto.id(),
                    dto.name(),
                    dto.socialName(),
                    dto.fatherName(),
                    dto.motherName(),
                    dto.cpf(),
                    dto.birthDate(),
                    dto.email(),
                    dto.street(),
                    null,
                    dto.zipCode()
                );
    }

    public static Person dtoToEntity(Long id, PersonDto dto){
        if(Objects.isNull(dto)){ return null;}

        return new Person(id,
                dto.name(),
                dto.socialName(),
                dto.fatherName(),
                dto.motherName(),
                dto.cpf(),
                dto.birthDate(),
                dto.email(),
                dto.street(),
                null,
                dto.zipCode()
        );
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


}
