package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Long appCode
    ) {}
