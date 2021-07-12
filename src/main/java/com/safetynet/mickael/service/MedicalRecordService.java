package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.model.MedicalRecord;

public interface MedicalRecordService {

	Iterable<MedicalRecord> list();
	MedicalRecord save(MedicalRecord medicalRecord);
	Iterable<MedicalRecord> save(List<MedicalRecord> medicalRecords);
}
