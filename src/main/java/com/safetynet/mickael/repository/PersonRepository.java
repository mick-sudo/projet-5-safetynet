package com.safetynet.mickael.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.model.pk.PersonPk;

public interface PersonRepository extends JpaRepository<Person, PersonPk>{

}
