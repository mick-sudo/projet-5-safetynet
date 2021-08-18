package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.dto.CoverageDTO;
import com.safetynet.mickael.dto.FoyerDTO;
import com.safetynet.mickael.model.Firestation;


public interface IFirestationService {

	List<String> getPhoneByStation(String firestation);

	List<CoverageDTO> getCoverageByFireStation(String stationNumber);

	List<FoyerDTO> getFoyerByFireStation(List<String> stations);


}
