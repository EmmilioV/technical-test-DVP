package com.dvp.technical_test.infrastructure.restapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvp.technical_test.application.dtos.person.PersonDTO;
import com.dvp.technical_test.application.usecases.PersonUsecases;
import com.dvp.technical_test.infrastructure.exception.MissingFieldsException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Usuarios", description = "Endpoints para gestión de usuarios")
@RestController()
@RequestMapping(value = "/persons")
public class PersonController {

    private final PersonUsecases personUsecases;

    public PersonController(PersonUsecases personUsecases) {
        this.personUsecases = personUsecases;
    }

    @PostMapping("/create-one")
    public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return personUsecases.create(personDTO);
    }

    @PutMapping("/update-one")
    public PersonDTO putMethodName(@Valid @RequestBody PersonDTO personDTO) {
        if (personDTO.getId() == null || personDTO.getId().isBlank()) {
            throw new MissingFieldsException("id is required for update");
        }

        if (personDTO.getCreatedDate() == null) {
            throw new MissingFieldsException("created date is missing");
        }

        return personUsecases.update(personDTO);
    }

    @GetMapping("/get-all")
    public List<PersonDTO> getMethodName() {
        return personUsecases.getAll();
    }

    @GetMapping("/get-one/{id}")
    public PersonDTO getMethodName(@PathVariable String id) {
        return personUsecases.getByID(id);
    }

}
