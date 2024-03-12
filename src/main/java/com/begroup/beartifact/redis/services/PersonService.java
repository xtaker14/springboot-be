package com.begroup.beartifact.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.begroup.beartifact.redis.models.Person;
import com.begroup.beartifact.redis.repositories.PersonRepository;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person) {
        try {
            personRepository.save(person);
            logger.info("Saved person with id: {}", person.getId());
            return person;
        } catch (Exception e) {
            logger.error("Error saving person: ", e);
            return null;
        }
    }

    public List<Person> findAllPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    public Person findPerson(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
