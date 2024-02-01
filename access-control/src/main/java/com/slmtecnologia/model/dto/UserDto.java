package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Email
        String email,
        List<Long> roleIds
) {
}
