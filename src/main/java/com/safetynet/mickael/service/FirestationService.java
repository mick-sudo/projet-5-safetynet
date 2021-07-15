package com.safetynet.mickael.service;

import java.util.Set;

import com.safetynet.mickael.dto.FirestationDTO;

public interface FirestationService {

	void save(FirestationDTO dto);
	void save(Set<FirestationDTO> dtos);
}
