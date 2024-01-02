package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.CityDto;
import com.slmtecnologia.repository.entity.City;

import java.util.Objects;

public class CityMapper {

    private CityMapper() {}

    public static City dtoToEntity(CityDto dto){
        return new City(dto.id(),
                dto.name(),
                dto.ibgeCode(),
                StateMapper.dtoToEntity(dto.state())
        );
    }

    public static CityDto entityToDto(City entity){
        return new CityDto(entity.getId(),
                entity.getName(),
                entity.getIbgeCode(),
                Objects.nonNull(entity.getState()) ? StateMapper.entityToDto(entity.getState()) : null
        );
    }
}
