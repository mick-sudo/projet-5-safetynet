package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.dto.FireAddressDTO;

public interface PersonService {

	List<FireAddressDTO> findFireByAdress(String address);
}
