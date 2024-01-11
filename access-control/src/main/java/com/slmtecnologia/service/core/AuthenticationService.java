package com.slmtecnologia.service.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slmtecnologia.controller.exceptions.InvalidJwtAuthenticationException;
import com.slmtecnologia.controller.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.*;
import com.slmtecnologia.model.entity.Token;
import com.slmtecnologia.repository.TokenRepository;
import com.slmtecnologia.model.enuns.TokenType;
import com.slmtecnologia.model.entity.User;
import com.slmtecnologia.repository.UserRepository;
import com.slmtecnologia.controller.exceptions.RequiredObjectIsNullException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        if(repository.findByEmail(user.getEmail()).isPresent()){
            throw new RequiredObjectIsNullException("User already exists");
        }
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken,refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken,jwtRefreshToken);

    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = new AuthenticationResponse(accessToken,refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    @SneakyThrows
    public String generateTokenFromPassword(User user) {
        KeyBasedPersistenceTokenService tokenService = getInstanceFor(user);

        org.springframework.security.core.token.Token token = tokenService.allocateToken(user.getEmail());
        return token.getKey();
    }
    @SneakyThrows
    public void changeForgotPassword(String newPassword, String rawToken){
        PasswordTokenPublicData publicData = readPublicDate(rawToken);

        if(isExpired(publicData)){
            throw new InvalidJwtAuthenticationException("Expired token");
        }

        var user = repository.findByEmail(publicData.email()).orElseThrow(() -> new ResourceNotFoundException("E-mail not found!"));

        KeyBasedPersistenceTokenService tokenService = getInstanceFor(user);
        tokenService.verifyToken(rawToken);

        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    private boolean isExpired(PasswordTokenPublicData publicData) {
        Instant createdAt = new Date(publicData.createdAtTimestamp()).toInstant();
        Instant now =new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(10)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(User user) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
        tokenService.setServerSecret(user.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }
    private PasswordTokenPublicData readPublicDate(String rawToken) {
        String rawTokenDecoded = new String(Base64.getDecoder().decode(rawToken));
        String[] tokenParts = rawTokenDecoded.split(":");
        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];

        return new PasswordTokenPublicData(email, timestamp);
    }

    public void changeForgotPasswordSendEmail(EmailResetRequest input) {

        Optional<User> userOptional = repository.findByEmail(input.email());
        userOptional.ifPresent(user -> {
            String token = generateTokenFromPassword(user);
            log.info(token);
            // send email
        });
    }
}
