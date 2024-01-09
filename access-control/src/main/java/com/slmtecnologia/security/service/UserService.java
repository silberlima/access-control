package com.slmtecnologia.security.service;

import com.slmtecnologia.security.model.dto.ChangePasswordRequest;
import com.slmtecnologia.security.model.dto.UserResponse;
import com.slmtecnologia.security.model.entity.User;
import com.slmtecnologia.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if(passwordEncoder.matches(passwordEncoder.encode(request.getCurrentPassword()), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }

    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastname(user.getLastName())
                        .build())
                .toList();
    }

}
