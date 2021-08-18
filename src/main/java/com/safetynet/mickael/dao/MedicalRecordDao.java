package com.safetynet.mickael.dao;

import com.safetynet.mickael.model.MedicalRecord;

public interface MedicalRecordDao {
	
	boolean createMedicalRecord(MedicalRecord medicalRecord);

	boolean updateMedicalRecord(MedicalRecord medicalRecord);

	boolean deleteMedicalRecord(MedicalRecord medicalRecord);
}
