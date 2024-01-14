package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetRequest(
        @NotBlank String password
    ) {}
