package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PermissionDto(
        Long id,
        @NotBlank String name,
        @NotBlank String description
) {}
