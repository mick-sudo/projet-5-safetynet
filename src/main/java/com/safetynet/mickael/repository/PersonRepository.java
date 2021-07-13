package com.safetynet.mickael.repository;

import java.util.List;

import javax.persistence.NamedNativeQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.model.pk.PersonPk;

public interface PersonRepository extends JpaRepository<Person, PersonPk> {

	List<Person> findPersonsByCity(String city);
	
	//@Query("FROM Person p where p.firestation.number = :number")
	//List<Person> findPersonByFirestationNumber(String number);
}
