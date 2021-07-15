package com.safetynet.mickael.service;

import java.util.Set;

import com.safetynet.mickael.dto.MedicalRecordDTO;

public interface MedicalRecordService {

	void save(MedicalRecordDTO dto);

	void save(Set<MedicalRecordDTO> dtos);

}
