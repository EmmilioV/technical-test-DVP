package com.dvp.technical_test.application.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dvp.technical_test.application.dtos.person.PersonConverter;
import com.dvp.technical_test.application.dtos.person.PersonDTO;
import com.dvp.technical_test.application.exception.BusinessException;
import com.dvp.technical_test.domain.person.entity.Person;
import com.dvp.technical_test.domain.person.repository.PersonRepository;

@Service
public class PersonUsecases {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonUsecases(PersonRepository personRepository, PersonConverter personConverter) {
        this.personConverter = personConverter;
        this.personRepository = personRepository;
    }

    public PersonDTO create(PersonDTO personDTO) {
        personDTO.setCreatedDate(LocalDateTime.now());
        personDTO.setUpdatedDate(LocalDateTime.now());

        Person personSaved = personRepository.save(personConverter.toPersonfromPersonDTO(personDTO));

        return personConverter.toPersonDTOfromPerson(personSaved);
    }

    public PersonDTO update(PersonDTO personDTO) {
        personDTO.setUpdatedDate(LocalDateTime.now());

        Person person = personConverter.toPersonfromPersonDTO(personDTO);

        Optional<Person> personFound = personRepository.findById(person.getId());
        if (!personFound.isPresent()) {
            throw new BusinessException("person not found");
        }

        return personConverter.toPersonDTOfromPerson(personRepository.save(person));
    }

    public List<PersonDTO> getAll() {
        return personRepository.findAll()
                .stream()
                .map(personConverter::toPersonDTOfromPerson)
                .toList();
    }

    public PersonDTO getByID(UUID id) {
        Optional<Person> personFound = personRepository.findById(id);
        if (!personFound.isPresent()) {
            throw new BusinessException("person not found");
        }

        return personConverter.toPersonDTOfromPerson(personFound.get());
    }
}
