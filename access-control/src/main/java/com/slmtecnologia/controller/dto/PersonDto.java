package com.slmtecnologia.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonDto(Long id,
                        @NotBlank(message = "Campo nome é obrigatório") String name,
                        @NotBlank(message = "Campo CPF é obrigatório")String cpf,
                        String email) {

}

