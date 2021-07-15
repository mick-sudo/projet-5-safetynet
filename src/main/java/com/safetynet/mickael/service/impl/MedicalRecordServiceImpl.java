package com.safetynet.mickael.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.MedicalRecordDTO;
import com.safetynet.mickael.dto.PersonDTO;
import com.safetynet.mickael.model.Allergie;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Medication;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.model.pk.PersonPk;
import com.safetynet.mickael.repository.MedicalRecordRepository;
import com.safetynet.mickael.repository.PersonRepository;
import com.safetynet.mickael.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PersonRepository personRepository;

	private MedicalRecord convertMedicalRecordDTOTomedicalRecord(MedicalRecordDTO dto) {
		MedicalRecord medicaleRecord = new MedicalRecord();
		medicaleRecord.setFirstName(dto.getFirstName());
		medicaleRecord.setLastName(dto.getLastName());

		Set<Medication> medications = new HashSet<>();
		for (String med : dto.getMedications()) {
			Medication medication = new Medication();
			medication.setName(med);
			medications.add(medication);
		}

		medicaleRecord.setMedications(medications);

		Set<Allergie> allergies = new HashSet<>();
		for (String all : dto.getAllergies()) {
			Allergie allergie = new Allergie();
			allergie.setName(all);
			allergies.add(allergie);
		}

		medicaleRecord.setAllergies(allergies);

		// recuperation de la personne si elle existe
		PersonPk pk = new PersonPk();
		pk.setFirstName(dto.getFirstName());
		pk.setLastName(dto.getLastName());

		Optional<Person> optionalPerson = this.personRepository.findById(pk);
		if (optionalPerson.isPresent()) {
			Person p = optionalPerson.get();
			p.setBirthdate(dto.getBirthdate());
			medicaleRecord.setPerson(p);
		} else {
			Person p = new Person();
			p.setFirstName(dto.getFirstName());
			p.setLastName(dto.getLastName());
			p.setBirthdate(dto.getBirthdate());
			medicaleRecord.setPerson(p);
		}
		return medicaleRecord;
	}

	@Override
	public void save(MedicalRecordDTO dto) {
		MedicalRecord medicalRecord = this.convertMedicalRecordDTOTomedicalRecord(dto);
		this.medicalRecordRepository.save(medicalRecord);
	}

	@Override
	public void save(Set<MedicalRecordDTO> dtos) {
		Set<MedicalRecord> medicalRecords = new HashSet<>();

		for (MedicalRecordDTO dto : dtos) {
			MedicalRecord p = this.convertMedicalRecordDTOTomedicalRecord(dto);
			medicalRecords.add(p);
		}

		this.medicalRecordRepository.saveAll(medicalRecords);
	}
}
