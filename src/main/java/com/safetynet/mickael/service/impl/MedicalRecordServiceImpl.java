package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.MedicalRecordDTO;
import com.safetynet.mickael.model.Allergie;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Medication;
import com.safetynet.mickael.repository.MedicalRecordRepository;
import com.safetynet.mickael.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl  implements MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public Iterable<MedicalRecord> list() {
		return medicalRecordRepository.findAll();
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		return medicalRecordRepository.save(medicalRecord);
	}

	public Iterable<MedicalRecord> save(List<MedicalRecordDTO> medicalRecords) {
		List<MedicalRecord> listeMedicalRecords = new ArrayList<>();
		
		for (MedicalRecordDTO dto : medicalRecords) {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setFirstName(dto.getFirstName());
			medicalRecord.setLastName(dto.getLastName());
			medicalRecord.setBirthdate(dto.getBirthdate());
			
			Set<Medication> setmedication = new HashSet<>();
			for (String medication : dto.getMedications()) {
				Medication m = new Medication();
				m.setName(medication);
				setmedication.add(m);
			}
			
			medicalRecord.setMedications(setmedication);
			
			Set<Allergie> setallergie = new HashSet<>();
			for (String allergie : dto.getAllergies()) {
				Allergie a = new Allergie();
				a.setName(allergie);
				setallergie.add(a);
			}
			
			medicalRecord.setAllergies(setallergie);
			listeMedicalRecords.add(medicalRecord);
		}
		return medicalRecordRepository.saveAll(listeMedicalRecords);
	}
}
