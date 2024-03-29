package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record StateDto(
        @NotBlank String acronym,
        @NotBlank String name
    ) {}
