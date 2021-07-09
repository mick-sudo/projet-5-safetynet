package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.PersonRepository;
import com.safetynet.mickael.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;

	public Iterable<Person> list() {
		return personRepository.findAll();
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public Iterable<Person> save(List<Person> persons) {
		return personRepository.saveAll(persons);
	}

	@Override
	public List<String> getCommunityMail(String city) {
		List<Person> listPersons = personRepository.findPersonsByCity(city);
		List<String> listEmails = new ArrayList<String>();
		
		for (Person person : listPersons) {
			listEmails.add(person.getEmail());
		}
		
		return listEmails;
	}
	

}
