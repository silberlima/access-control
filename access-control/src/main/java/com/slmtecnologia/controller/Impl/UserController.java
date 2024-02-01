package com.slmtecnologia.controller.Impl;

import com.slmtecnologia.controller.IUserController;
import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.UserDto;
import com.slmtecnologia.model.dto.UserResponse;
import com.slmtecnologia.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService service;

    @Override
    @GetMapping(value = "/findByName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserResponse>> findByName(
            @PathVariable(value = "firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

        return new ResponseEntity<>(service.findByName(firstName, pageable), HttpStatus.OK);
    }
    @Override
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        service.changePassword(request, connectedUser);
        return  ResponseEntity.accepted().build();
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(service.create(userDto), HttpStatus.CREATED);
    }

}
