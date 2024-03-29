package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CityDto(
        Long id,
        @NotBlank String name,
        @NotBlank String ibgeCode,
        @NotBlank String stateAcronym
    ) {}
