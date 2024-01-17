package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record PermissionDto(
        Long id,
        @NotBlank String name,
        @NotBlank String description,
        Long roleId
    ) {}
