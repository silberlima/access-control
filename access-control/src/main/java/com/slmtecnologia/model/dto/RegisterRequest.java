package com.slmtecnologia.model.dto;

import com.slmtecnologia.model.enuns.Role;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String firstName,
        @NotBlank String lastname,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank Role role
    ) {}
