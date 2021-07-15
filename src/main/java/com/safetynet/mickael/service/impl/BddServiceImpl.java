package com.safetynet.mickael.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.BddDTO;
import com.safetynet.mickael.service.BddService;
import com.safetynet.mickael.service.FirestationService;
import com.safetynet.mickael.service.MedicalRecordService;
import com.safetynet.mickael.service.PersonService;

@Service
public class BddServiceImpl implements BddService {

	@Autowired
	private PersonService personService;

	@Autowired
	private FirestationService firestationService;

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	public void save(BddDTO dto) {
		this.personService.save(dto.getPersons());
		this.firestationService.save(dto.getFirestations());
		this.medicalRecordService.save(dto.getMedicalrecords());
	}
}
