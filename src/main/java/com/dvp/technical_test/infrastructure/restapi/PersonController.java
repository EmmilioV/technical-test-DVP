package com.dvp.technical_test.infrastructure.restapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvp.technical_test.application.usecases.PersonUsecases;
import com.dvp.technical_test.domain.person.entity.Person;

@RestController()
@RequestMapping(value = "/persons")
public class PersonController {
    
    private final PersonUsecases personUsecases;

    public PersonController(PersonUsecases personUsecases) {
        this.personUsecases = personUsecases;
    }

    @PostMapping("/create-one")
    public Person createPerson(@RequestBody Person person) {
        return personUsecases.createPerson(person);
    }
}
