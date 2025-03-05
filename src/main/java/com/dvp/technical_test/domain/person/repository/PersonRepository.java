package com.dvp.technical_test.domain.person.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvp.technical_test.domain.person.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID>{
}
