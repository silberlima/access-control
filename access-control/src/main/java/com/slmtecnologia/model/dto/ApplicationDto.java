package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ApplicationDto(
        Long id,
        @NotBlank String name
    ) {}
