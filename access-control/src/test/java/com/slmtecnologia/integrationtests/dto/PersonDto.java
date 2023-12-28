package com.slmtecnologia.integrationtests.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonDto(Long id,
                        @NotBlank(message = "Campo nome é obrigatório") String name,
                        String cpf,
                        String email) {

}

