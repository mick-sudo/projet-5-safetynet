package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;
import com.safetynet.mickael.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private DataRepository dataRepository;
	
	@Override
	public List<String> getCommunityEmail(String city) {
		List<String> emails = new ArrayList<String>();
		List<Person> persons = dataRepository.getPersonByCity(city);
		
		for(Person person : persons) {
			emails.add(person.getEmail());
		}
		return emails;
	}


	
}
