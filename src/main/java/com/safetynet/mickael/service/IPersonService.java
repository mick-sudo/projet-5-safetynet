package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.dto.FireDTO;
import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.PersonInfoDTO;

public interface IPersonService {

	List<String> getCommunityEmail(String city);

	List<ChildAlertDTO> getChildAlert(String address);

	List<PersonInfoDTO> getpersonInfo(String firstName, String lastName);

	List<FireDTO> getPersonByAddress(String address);

	
}

