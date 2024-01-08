package com.slmtecnologia.security.controller;

import com.slmtecnologia.security.model.dto.ChangePasswordRequest;
import com.slmtecnologia.security.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @PatchMapping
    @PreAuthorize("hadAuthority('user:read')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            Principal connectedUser){
        userService.changePassword(request, connectedUser);

        return  ResponseEntity.accepted().build();
    }

}
