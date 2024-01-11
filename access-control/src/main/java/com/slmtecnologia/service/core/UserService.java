package com.slmtecnologia.service.core;

import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.UserResponse;
import com.slmtecnologia.model.entity.User;
import com.slmtecnologia.repository.UserRepository;
import com.slmtecnologia.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    @Override
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

    @Override
    public Page<UserResponse> findByName(String name, Pageable pageable) {
        var users = repository.findByName(name, pageable);
        return users.map(user -> UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastname(user.getLastName())
                .build());
    }

}
