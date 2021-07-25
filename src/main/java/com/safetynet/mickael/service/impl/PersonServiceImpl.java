package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.FireAddressDTO;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.FirestationRepository;
import com.safetynet.mickael.repository.MedicalRecordRepository;
import com.safetynet.mickael.repository.PersonRepository;
import com.safetynet.mickael.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRepository;


	@Override
	public List<FireAddressDTO> findFireByAdress(String address) {
		List<FireAddressDTO> fireAddressDTO = new ArrayList<FireAddressDTO>();
		for(Person person : personRepository.findbyAddress(address)) {
			
			FireAddressDTO dto = new FireAddressDTO();
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setPhone(person.getPhone());
			MedicalRecord medicalRecordPerson = medicalRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
			dto.setAllergies(medicalRecordPerson.getAllergies());
			dto.setMedications(medicalRecordPerson.getMedications());
			dto.setAge(15);
			
			dto.setStation(address);
		}
		return fireAddressDTO;
	}
	
	
}
