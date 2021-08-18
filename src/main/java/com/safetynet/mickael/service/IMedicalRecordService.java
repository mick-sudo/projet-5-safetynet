package com.safetynet.mickael.service;

import com.safetynet.mickael.model.MedicalRecord;

public interface IMedicalRecordService {
	void createMedicalRecord(MedicalRecord medicalRecord);

	void updateMedicalRecord(MedicalRecord medicalRecord);

	void deleteMedicalRecord(MedicalRecord medicalRecord);
}
