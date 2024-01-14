package com.slmtecnologia.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PersonDto(
        @Schema(description = "Id do registro",name = "id",type = "Long",example = "10")
        Long id,
        @Schema(description = "Nome da pessoa",name = "name",type = "string",example = "João da Silva")
        @NotBlank(message = "Campo nome é obrigatório") String name,
        @Schema(description = "Nome Social",name = "socialName",type = "string",example = "Joaquina")
        String socialName,
        @Schema(description = "Nome do pai",name = "fatherName",type = "string",example = "José da Silva")
        String fatherName,
        @Schema(description = "Nome da mãe",name = "motherName",type = "string",example = "Maria da Silva")
        String motherName,
        @Schema(description = "CPF da pessoa",name = "cpf",type = "String",example = "00011122233")
        @NotBlank(message = "Campo CPF é obrigatório") String cpf,
        @Schema(description = "Data de nascimento da pessoa",name = "birthDate",type = "LocalDate",example = "1985-01-31")
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
        @Schema(description = "E-mail da pessoa",name = "email",type = "string",example = "joao@mail.com")
        @NotBlank(message = "Campo email é obrigatório") String email,
        @Schema(description = "Endereço da pessoa",name = "street",type = "string",example = "Rua 03 de Outubro")
        String street,
        @Schema(description = "Codigo da cidade do endereço",name = "cityId",type = "Long",example = "1")
        Long cityId,
        @Schema(description = "CEP da pessoa",name = "zipCode",type = "string",example = "08090-284")
        String zipCode
    ) {}

