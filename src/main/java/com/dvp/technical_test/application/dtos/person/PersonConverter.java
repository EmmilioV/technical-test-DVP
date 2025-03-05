package com.dvp.technical_test.application.dtos.person;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dvp.technical_test.domain.person.entity.Person;

@Service
public class PersonConverter {
    public Person toPersonfromPersonDTO(PersonDTO personDTO) {
        return Person.builder()
                .id(personDTO.getId() == null || personDTO.getId().isBlank()
                        ? null
                        : UUID.fromString(personDTO.getId()))
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .createdDate(personDTO.getCreatedDate())
                .updatedDate(personDTO.getUpdatedDate())
                .build();
    }

    public PersonDTO toPersonDTOfromPerson(Person person) {
        return PersonDTO.builder()
                .id(person.getId().toString())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .createdDate(person.getCreatedDate())
                .updatedDate(person.getUpdatedDate())
                .build();
    }
}
