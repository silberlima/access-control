package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.UserResponse;
import com.slmtecnologia.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for Managing Users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "/findByName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find Users by name", description = "Find Users by name",
            tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                            )
                    }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Page<UserResponse>> findByName(
            @PathVariable(value = "firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));

        return new ResponseEntity<>(userService.findByName(firstName, pageable), HttpStatus.OK);
    }
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                  consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Change password User", description = "Change password User",
            tags = {"Users"}, responses = {
            @ApiResponse(description = "Accepted", responseCode = "202",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        userService.changePassword(request, connectedUser);
        return  ResponseEntity.accepted().build();
    }
}
