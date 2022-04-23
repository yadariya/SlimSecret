package com.example.slimsecret.controller;

import com.example.slimsecret.model.Person;
import com.example.slimsecret.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class LogInSignUpController {

    private final PersonRepository personRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Person person) {
        if (ifExists(person.getAlias())) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        personRepository.save(person);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Person person) {
        if (ifExists(person.getAlias())) {
            Optional<Person> person_new = personRepository.findPersonByAlias(person.getAlias());
            personRepository.deleteById(person_new.get().getId());
            personRepository.save(person);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Such user not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<Person> logInUser(@RequestBody Person person_) {
        if (ifExists(person_.getAlias())) {
            Person person = personRepository.findPersonByAlias(person_.getAlias()).get();
            if (person_.getPassword().equals(person.getPassword())) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public boolean ifExists(String alias) {
        Optional<Person> person = personRepository.findPersonByAlias(alias);
        return person.isPresent();
    }
}


