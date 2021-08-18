package com.safetynet.mickael.service;

import java.util.List;

import javax.validation.Valid;

import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.FireDTO;
import com.safetynet.mickael.dto.PersonInfoDTO;
import com.safetynet.mickael.model.Person;

public interface IPersonService {

	List<String> getCommunityEmail(String city);

	List<ChildAlertDTO> getChildAlert(String address);

	List<PersonInfoDTO> getpersonInfo(String firstName, String lastName);

	List<FireDTO> getPersonByAddress(String address);

	boolean createPerson(@Valid Person person);

	boolean updatePerson(@Valid Person person);

	boolean deletePerson(@Valid Person person);
	

	

	
}


