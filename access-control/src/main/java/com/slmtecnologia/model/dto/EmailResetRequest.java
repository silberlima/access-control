package com.slmtecnologia.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailResetRequest(@Email @NotBlank String email) {
}
