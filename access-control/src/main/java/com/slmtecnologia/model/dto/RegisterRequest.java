package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String firstName,
        @NotBlank String lastname,
        @NotBlank String email,
        @NotBlank String password
    ) {}
