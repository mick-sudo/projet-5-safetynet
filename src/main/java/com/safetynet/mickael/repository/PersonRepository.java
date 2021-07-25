package com.safetynet.mickael.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.mickael.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

}
