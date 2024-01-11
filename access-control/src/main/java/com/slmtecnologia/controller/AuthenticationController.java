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

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Endpoints for Managing Authentications")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody @Valid EmailResetRequest resetRequest){
        service.changeForgotPasswordSendEmail(resetRequest);
    }

    @PatchMapping("/update-forgot-password/{token}")
    public void updateForgotPassword(
            @PathVariable("token") String token,
            @RequestBody @Valid PasswordResetRequest passwordResetRequest){
        try{
            service.changeForgotPassword(passwordResetRequest.password(),token);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
