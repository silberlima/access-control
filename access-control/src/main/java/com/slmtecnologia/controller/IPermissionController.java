package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.PermissionDto;
import com.slmtecnologia.model.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Permission", description = "Endpoints for Managing Permissions")
@PreAuthorize("hasRole('ADMIN')")
public interface IPermissionController {
    static final String TAG  = "Permission";
    @Operation(summary = "Find all "+TAG, description = "Find all "+TAG,
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PermissionDto.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PreAuthorize("hasAuthority('permission:read')")
    public ResponseEntity<Page<PermissionDto>> findAll(Integer page,Integer size,String direction);

    @Operation(summary = "Find a "+TAG, description = "Find a Role"+TAG,
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PreAuthorize("hasAuthority('permission:read')")
    ResponseEntity<PermissionDto> findById(Long id);


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new "+TAG, description = "Add a new a "+TAG,
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PreAuthorize("hasAuthority('permission:create')")
    ResponseEntity<PermissionDto> create(@RequestBody @Valid PermissionDto dto);
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a "+TAG, description = "Update a "+TAG,
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PreAuthorize("hasAuthority('permission:update')")
    ResponseEntity<PermissionDto> update(Long id, PermissionDto dto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a "+TAG, description = "Delete a "+TAG,
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PreAuthorize("hasAuthority('permission:delete')")
    ResponseEntity<Void> delete(Long id);

}
