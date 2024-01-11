package com.slmtecnologia.model.dto;

public record CityDto(
        Long id,
        String name,
        String ibgeCode,
        StateDto state
    ) {}
