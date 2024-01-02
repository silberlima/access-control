package com.slmtecnologia.controller.mapper;

import com.slmtecnologia.controller.dto.StateDto;
import com.slmtecnologia.entity.State;

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
