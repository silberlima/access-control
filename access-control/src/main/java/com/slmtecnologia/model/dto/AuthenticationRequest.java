package com.slmtecnologia.model.dto;

public record AuthenticationRequest(
        String email,
        String password
    ) {}
