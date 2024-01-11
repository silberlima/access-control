package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PersonDetailDto(
        Long id,
        String socialName,
        String fatherName,
        String motherName,
        LocalDate birthDate,
        String email,
        String street,
        CityDto city,
        String zipCode
) {}
