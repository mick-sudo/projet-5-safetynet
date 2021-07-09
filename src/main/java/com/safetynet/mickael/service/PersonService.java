package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.model.Person;


public interface PersonService {

	Iterable<Person> list();
	Person save(Person person);
	Iterable<Person> save(List<Person> persons);
	List<String> getCommunityMail(String city);
}
