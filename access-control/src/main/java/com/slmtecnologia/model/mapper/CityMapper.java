package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.CityDto;
import com.slmtecnologia.model.entity.City;

import java.util.Objects;

public class CityMapper {

    private CityMapper() {}

    public static City dtoToEntity(CityDto dto){
        return new City(dto.id(),
                dto.name(),
                dto.ibgeCode(),
                null
        );
    }

    public static CityDto entityToDto(City entity){

        return new CityDto(entity.getId(),
                entity.getName(),
                entity.getIbgeCode(),
                Objects.nonNull(entity.getState()) ? entity.getState().getAcronym(): null
        );
    }
}
