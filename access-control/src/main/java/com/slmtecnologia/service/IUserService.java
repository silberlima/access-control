package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.UserDto;
import com.slmtecnologia.model.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface IUserService {

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    Page<UserResponse> findByName(String name, Pageable pageable);

    UserDto create(UserDto userDto);
}
