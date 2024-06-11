package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationDto(
        Long id,
        @NotBlank String name,

        @NotNull Long appCode
    ) {}
