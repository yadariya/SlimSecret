package com.example.slimsecret.repository;

import com.example.slimsecret.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findPersonByAlias(String alias);
}
