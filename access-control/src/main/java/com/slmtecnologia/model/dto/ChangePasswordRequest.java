package com.slmtecnologia.model.dto;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword,
        String confirmationPassword
    ){}
