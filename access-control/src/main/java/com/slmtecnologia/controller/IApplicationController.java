package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.ApplicationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@Tag(name = "Applications", description = "Endpoints for Managing Applications")
@PreAuthorize("hasRole('ADMIN')")
public interface IApplicationController {

    static final String TAG  = "Applications";

    @Operation(summary = "Find "+TAG+" by name", description = "Find "+TAG+" by name",
            tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ApplicationDto.class))
                            )
                    }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    @PreAuthorize("hasAuthority('application:read')")
    ResponseEntity<Page<ApplicationDto>> findAll(Integer page,Integer size,String direction);

    @Operation(summary = "Find a "+TAG, description = "Find a "+TAG,
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = ApplicationDto.class))),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('application:read')")
    ResponseEntity<ApplicationDto> findById(Long id);

    @Operation(summary = "Add a new "+TAG, description = "Add a new a "+TAG,
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = ApplicationDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('application:create')")
    ResponseEntity<ApplicationDto> create(ApplicationDto applicationDto);

    @Operation(summary = "Update a "+TAG, description = "Update a "+TAG,
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = ApplicationDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('application:update')")
    ResponseEntity<ApplicationDto> update(Long id,ApplicationDto dto);

    @Operation(summary = "Delete a "+TAG, description = "Delete a "+TAG,
        tags = {TAG}, responses = {
            @ApiResponse(description = "No content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('application:delete')")
    ResponseEntity<Void> deleteApplication(Long id);

    @Operation(summary = "Find "+TAG+" by name", description = "Find "+TAG+" by name",
            tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ApplicationDto.class))
                            )
                    }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    @PreAuthorize("hasAuthority('application:read')")
    ResponseEntity<Page<ApplicationDto>> findByName(String name,Integer page,Integer size,String direction);
}
