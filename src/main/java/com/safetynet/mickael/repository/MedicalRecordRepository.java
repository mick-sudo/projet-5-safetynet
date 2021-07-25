package com.safetynet.mickael.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.mickael.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String>{

	MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName);
}
