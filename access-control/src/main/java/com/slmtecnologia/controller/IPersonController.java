package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoints for Managing Peoples")
@PreAuthorize("hasRole('ADMIN')")
public interface IPersonController {

    static final String TAG = "People" ;
    @Operation(summary = "Find all "+TAG, description = "Find all "+TAG,
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = {
                @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('person:read')")
    ResponseEntity<Page<PersonDto>> findAll(Integer page,Integer size,String direction);

    @Operation(summary = "Find a Resource", description = "Find a Resource",
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('person:read')")
    ResponseEntity<PersonDto> findById(Long id);

    @Operation(summary = "Add a new Resource", description = "Add a new a Resource",
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('person:create')")
    public ResponseEntity<PersonDto> create(PersonDto personDto);

    @Operation(summary = "Update a Resource", description = "Update a Resource",
        tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",content = @Content(schema = @Schema(implementation = PersonDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('person:update')")
    ResponseEntity<PersonDto> update(@PathVariable Long id, PersonDto dto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Resource", description = "Delete a Resource",
        tags = {TAG}, responses = {
            @ApiResponse(description = "No content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PreAuthorize("hasAuthority('person:delete')")
    ResponseEntity<Void> delete(Long id);

    @Operation(summary = "Find people by name", description = "Find people by name",
            tags = {TAG}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                            )
                    }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    }
    )
    @PreAuthorize("hasAuthority('person:read')")
    ResponseEntity<Page<PersonDto>> findByName(String name,Integer page,Integer size,String direction );

}
