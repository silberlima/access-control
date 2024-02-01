package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RoleDto(
        Long id,
        @NotBlank String name,
        @NotBlank String description,
        List<PermissionDto> permissions
    ) {}
