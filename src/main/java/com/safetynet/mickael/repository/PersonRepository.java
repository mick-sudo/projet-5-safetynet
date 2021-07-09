package com.safetynet.mickael.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.model.pk.PersonPk;

public interface PersonRepository extends JpaRepository<Person, PersonPk> {

	List<Person> findPersonsByCity(String city);

}
