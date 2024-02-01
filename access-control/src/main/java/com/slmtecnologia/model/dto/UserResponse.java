package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserResponse(
        @NotBlank
        String firstName,
        String lastname,
        @NotBlank
        String email
    ) {}
