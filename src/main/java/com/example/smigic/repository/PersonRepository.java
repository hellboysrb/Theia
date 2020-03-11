package com.example.smigic.repository;

import com.example.smigic.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {
    Person findByFirstName(String firstName);
}
