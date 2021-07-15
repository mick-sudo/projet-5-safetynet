package com.safetynet.mickael.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.pk.PersonPk;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, PersonPk>{

}
