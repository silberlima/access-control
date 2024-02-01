package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDto(
        Long id,
        @NotBlank String name,
        @NotBlank String description
    ) {}
