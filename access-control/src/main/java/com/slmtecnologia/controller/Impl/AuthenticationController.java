package com.slmtecnologia.controller.Impl;

import com.slmtecnologia.controller.IAuthenticationController;
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
public class AuthenticationController implements IAuthenticationController {

    private final AuthenticationService service;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @Override
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @Override
    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody @Valid EmailResetRequest resetRequest){
        service.changeForgotPasswordSendEmail(resetRequest);
    }

    @Override
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
