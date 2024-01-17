package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.*;
import com.slmtecnologia.service.core.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Authentication", description = "Endpoints for Managing Authentications")
public interface IAuthenticationController {


    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException;

    void forgotPassword(EmailResetRequest resetRequest);

    void updateForgotPassword(String token,PasswordResetRequest passwordResetRequest);

}
