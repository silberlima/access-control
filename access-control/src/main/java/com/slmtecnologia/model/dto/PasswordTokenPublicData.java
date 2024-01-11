package com.slmtecnologia.model.dto;

public record PasswordTokenPublicData(
        String email,
        Long createdAtTimestamp
    ) {}
