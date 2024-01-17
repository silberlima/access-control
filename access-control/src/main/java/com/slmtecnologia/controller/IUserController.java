package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;

@Tag(name = "User", description = "Endpoints for Managing Users")
@PreAuthorize("hasRole('ADMIN')")
public interface IUserController {

    static final String TAG = "User";

    @Operation(summary = "Find "+TAG+" by name", description = "Find "+TAG+" by name",
            tags = {TAG}, responses = {
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
    ResponseEntity<Page<UserResponse>> findByName(String firstName,Integer page,Integer size,String direction);

    @Operation(summary = "Change password "+TAG, description = "Change password "+TAG,
            tags = {TAG}, responses = {
            @ApiResponse(description = "Accepted", responseCode = "202",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    ResponseEntity<?> changePassword(ChangePasswordRequest request, Principal connectedUser);
}
