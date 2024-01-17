package com.slmtecnologia.controller.Impl;

import com.slmtecnologia.controller.IPersonController;
import com.slmtecnologia.model.dto.PersonDto;
import com.slmtecnologia.service.IPersonService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/person")
public class PersonController implements IPersonController {

    private final IPersonService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PersonDto>> findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction

    ) {
        var sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto) {
        return new ResponseEntity<>(service.create(personDto), HttpStatus.CREATED);
    }
    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody @Valid PersonDto updatedPersonDto) {
        return new ResponseEntity<>(service.update(id, updatedPersonDto), HttpStatus.OK);
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Override
    @GetMapping(value = "/findByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PersonDto>> findByName(
            @PathVariable(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction

    ) {
        var sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        return new ResponseEntity<>(service.findByName(name, pageable), HttpStatus.OK);
    }

}
