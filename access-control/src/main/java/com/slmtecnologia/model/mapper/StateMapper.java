package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.StateDto;
import com.slmtecnologia.model.entity.State;

public class StateMapper {

    private StateMapper(){}

    public static State dtoToEntity(StateDto dto){
        return new State(dto.acronym(),
                dto.name());
    }

    public static StateDto entityToDto(State entity){
        return new StateDto(entity.getAcronym(),
                entity.getName());
    }
}
