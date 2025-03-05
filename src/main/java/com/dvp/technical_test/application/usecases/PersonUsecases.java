package com.dvp.technical_test.application.usecases;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dvp.technical_test.domain.person.entity.Person;
import com.dvp.technical_test.domain.person.repository.PersonRepository;

@Service
public class PersonUsecases {
    
    private final PersonRepository personRepository;

    public PersonUsecases(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {

        person.setCreatedDate(LocalDateTime.now());
        person.setUpdatedDate(LocalDateTime.now());

        try {
            return personRepository.save(person);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

}
