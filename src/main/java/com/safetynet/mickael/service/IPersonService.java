package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.dto.ChildAlertDTO;

public interface IPersonService {

	List<String> getCommunityEmail(String city);

	List<ChildAlertDTO> getChildAlert(String address);

}

