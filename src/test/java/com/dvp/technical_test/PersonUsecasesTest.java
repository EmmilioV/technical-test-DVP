package com.dvp.technical_test;

import com.dvp.technical_test.application.dtos.person.PersonConverter;
import com.dvp.technical_test.application.dtos.person.PersonDTO;
import com.dvp.technical_test.application.usecases.PersonUsecases;
import com.dvp.technical_test.domain.person.entity.Person;
import com.dvp.technical_test.domain.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonUsecasesTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonConverter personConverter;

    @InjectMocks
    private PersonUsecases personUsecases;

    private PersonDTO personDTO;
    private Person person;
    private PersonDTO savedPersonDTO;
    private Person savedPerson;

    @BeforeEach
    void setUp() {
        personDTO = PersonDTO.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .build();

        person = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .build();

        savedPerson = Person.builder()
                .id(UUID.randomUUID())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .build();

        savedPersonDTO = PersonDTO.builder()
                .id(savedPerson.getId().toString())
                .firstName(savedPerson.getFirstName())
                .lastName(savedPerson.getLastName())
                .build();
    }

    @Test
    void createPerson_ShouldReturnSavedPersonDTO() {
        savedPerson.setCreatedDate(LocalDateTime.now());
        savedPerson.setUpdatedDate(LocalDateTime.now());

        savedPersonDTO.setCreatedDate(savedPerson.getCreatedDate());
        savedPersonDTO.setUpdatedDate(savedPerson.getUpdatedDate());

        when(personConverter.toPersonfromPersonDTO(personDTO)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(savedPerson);
        when(personConverter.toPersonDTOfromPerson(savedPerson)).thenReturn(savedPersonDTO);

        PersonDTO result = personUsecases.create(personDTO);

        verify(personConverter).toPersonfromPersonDTO(personDTO);
        verify(personRepository).save(person);
        verify(personConverter).toPersonDTOfromPerson(savedPerson);

        assertNotNull(result);
        assertEquals(savedPersonDTO, result);
    }
}
