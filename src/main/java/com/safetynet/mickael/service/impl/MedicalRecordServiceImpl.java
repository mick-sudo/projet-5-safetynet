package com.safetynet.mickael.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.MedicalRecord;
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

	public Iterable<MedicalRecord> save(List<MedicalRecord> medicalRecords) {
		return medicalRecordRepository.saveAll(medicalRecords);
	}
}
