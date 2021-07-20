package com.safetynet.mickael.service;

import java.util.Set;

import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.FirestationPersonDTO;
import com.safetynet.mickael.dto.PersonDTO;
import com.safetynet.mickael.model.Person;

public interface PersonService {

	void save(PersonDTO dto);
	void save(Set<PersonDTO> dtos);
	FirestationPersonDTO oldFindByFirestationNumber(Integer stationNumber);
	FirestationPersonDTO findByFirestationNumber(Integer stationNumber);
	ChildAlertDTO findbyAddress(String address);
}
