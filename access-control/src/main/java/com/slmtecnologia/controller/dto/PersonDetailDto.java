package com.slmtecnologia.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PersonDetailDto(Long id,
                              @NotBlank(message = "Campo nome é obrigatório") String name,
                              String socialName,
                              String fatherName,
                              String motherName,
                              @NotBlank(message = "Campo CPF é obrigatório")String cpf,
                              LocalDate birthDate,
                              String email,
                              String street,
                              CityDto city,
                              String zipCode
) {
}
