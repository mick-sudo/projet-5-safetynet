package com.safetynet.mickael.service;

import java.util.Set;

import com.safetynet.mickael.dto.PersonDTO;

public interface PersonService {

	void save(PersonDTO dto);
	void save(Set<PersonDTO> dtos);

}
