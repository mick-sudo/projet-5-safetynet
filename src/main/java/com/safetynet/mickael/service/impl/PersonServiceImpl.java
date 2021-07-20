package com.safetynet.mickael.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.FirestationPersonDTO;
import com.safetynet.mickael.dto.PersonDTO;
import com.safetynet.mickael.model.Address;
import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.FirestationRepository;
import com.safetynet.mickael.repository.PersonRepository;
import com.safetynet.mickael.service.PersonService;
import com.safetynet.mickael.utils.PersonUtils;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;

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

	@Override
	public FirestationPersonDTO oldFindByFirestationNumber(Integer stationNumber) {
		FirestationPersonDTO firestationPersonDTO = new FirestationPersonDTO();
		Set<PersonDTO> personDTOs = new HashSet<PersonDTO>();
		int nbChildren = 0;
		int nbAdult = 0;

		Set<Person> persons = this.personRepository.findByFirestationNumber(stationNumber);

		for (Person person : persons) {
			// Creation du PersonDTO
			PersonDTO dto = new PersonDTO();

			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setPhone(person.getPhone());

			Address address = person.getAddress();

			dto.setAddress(address.getAddress());
			dto.setCity(address.getCity());
			dto.setZip(address.getZip());

			personDTOs.add(dto);

			// Indicateur majeur / mineur
			if (PersonUtils.isMineur(person.getBirthdate())) {
				nbChildren++;
			} else {
				nbAdult++;
			}
		}

		firestationPersonDTO.setNbAdult(nbAdult);
		firestationPersonDTO.setNbChildren(nbChildren);
		firestationPersonDTO.setPersons(personDTOs);

		return firestationPersonDTO;
	}

	@Override
	public FirestationPersonDTO findByFirestationNumber(Integer stationNumber) {
		FirestationPersonDTO firestationPersonDTO = new FirestationPersonDTO();
		Set<PersonDTO> personDTOs = new HashSet<PersonDTO>();
		int nbChildren = 0;
		int nbAdult = 0;

		// On récupère le firestation qui nous intéresse
		Optional<Firestation> optionalFirestation = this.firestationRepository.findById(stationNumber);

		if (optionalFirestation.isPresent()) {
			Firestation firestation = optionalFirestation.get();

			for (Address address : firestation.getAdresses()) {
				for (Person person : address.getPersons()) {
					// Creation du PersonDTO
					PersonDTO dto = new PersonDTO();

					dto.setFirstName(person.getFirstName());
					dto.setLastName(person.getLastName());
					dto.setPhone(person.getPhone());

					dto.setAddress(address.getAddress());
					dto.setCity(address.getCity());
					dto.setZip(address.getZip());

					personDTOs.add(dto);

					// Indicateur majeur / mineur
					if (PersonUtils.isMineur(person.getBirthdate())) {
						nbChildren++;
					} else {
						nbAdult++;
					}
				}
			}

			firestationPersonDTO.setNbAdult(nbAdult);
			firestationPersonDTO.setNbChildren(nbChildren);
			firestationPersonDTO.setPersons(personDTOs);
		}

		return firestationPersonDTO;
	}

	@Override
	public ChildAlertDTO findbyAddress(String address) {
		ChildAlertDTO childalert = new ChildAlertDTO();
		Set<PersonDTO> majeur = new HashSet<PersonDTO>();
		Set<PersonDTO> mineur = new HashSet<PersonDTO>();
		Set<Person> persons = this.personRepository.findbyAddress(address);
		for(Person person : persons) {
			PersonDTO dto = new PersonDTO();
			
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			
			int age = PersonUtils.getAge(person.getBirthdate());
			dto.setAge(age);
			
			if(age < 18) {
				mineur.add(dto);
			}else {
				majeur.add(dto);
			}
			
		}
		
		childalert.setAdults(majeur);
		childalert.setChildrens(mineur);
		
		if(mineur.isEmpty()) {
			return null;
		} else {
			return childalert;
		}
	}

}
