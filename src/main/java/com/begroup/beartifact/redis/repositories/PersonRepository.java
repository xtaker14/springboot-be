package com.begroup.beartifact.redis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.begroup.beartifact.redis.models.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
}
