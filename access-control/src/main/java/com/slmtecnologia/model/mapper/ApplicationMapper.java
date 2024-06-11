package com.slmtecnologia.model.mapper;

import com.slmtecnologia.model.dto.ApplicationDto;
import com.slmtecnologia.model.entity.Application;

public class ApplicationMapper {

    private ApplicationMapper() {}

    public static Application dtoToEntity(ApplicationDto dto){
        return new Application(dto.id(),
                dto.name(),
                dto.appCode());
    }

    public static Application dtoToEntity(Application application,ApplicationDto dto){
        return new Application(application.getId(),
                dto.name(),
                dto.appCode());
    }


    public static ApplicationDto entityToDto(Application entity){
        return new ApplicationDto(entity.getId(),
                entity.getName(),
                entity.getAppCode()
        );
    }
}
