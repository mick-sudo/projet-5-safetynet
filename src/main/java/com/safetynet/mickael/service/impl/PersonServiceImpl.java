package com.safetynet.mickael.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.PersonDTO;
import com.safetynet.mickael.model.Address;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.PersonRepository;
import com.safetynet.mickael.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	private Person convertPersonDTOToperson(PersonDTO dto) {
		Person person = new Person();
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setEmail(dto.getEmail());
		person.setPhone(dto.getPhone());

		Address address = new Address();
		address.setAddress(dto.getAddress());
		address.setCity(dto.getCity());
		address.setZip(dto.getZip());

		person.setAddress(address);

		return person;
	}

	@Override
	public void save(PersonDTO dto) {
		Person person = this.convertPersonDTOToperson(dto);
		this.personRepository.save(person);
	}

	@Override
	public void save(Set<PersonDTO> dtos) {
		Set<Person> persons = new HashSet<>();

		for (PersonDTO dto : dtos) {
			Person p = this.convertPersonDTOToperson(dto);
			persons.add(p);
		}

		this.personRepository.saveAll(persons);
	}

}
