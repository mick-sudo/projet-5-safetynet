package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.CoverageDTO;
import com.safetynet.mickael.dto.FireDTO;
import com.safetynet.mickael.dto.FoyerDTO;
import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;
import com.safetynet.mickael.service.IFirestationService;
import com.safetynet.mickael.service.IPersonService;
import com.safetynet.mickael.utils.PersonUtils;

@Service
public class FirestationServiceImpl implements IFirestationService {

	@Autowired
	private DataRepository dataRepository;

	@Autowired
	private IPersonService personService;


	@Override
	public List<String> getPhoneByStation(String firestation) {
		List<String> phones = new ArrayList<String>();
		for (Firestation fireStation : dataRepository.getFirestationByStation(firestation)) {
			for (Person person : dataRepository.getAllPerson()) {
				if (person.getAddress().equalsIgnoreCase(fireStation.getAddress())) {
					phones.add(person.getPhone());
				}
			}
		}
		return phones;
	}

	@Override
	public List<CoverageDTO> getCoverageByFireStation(String stationNumber) {
		List<CoverageDTO> coverageDTOs = new ArrayList<CoverageDTO>();
		List<Firestation> firestations = dataRepository.getFirestationByStation(stationNumber);
		int adultCount = 0;
		int childCount = 0;
		for (Firestation firestation : firestations) {
			List<Person> persons = dataRepository.getPersonByAddress(firestation.getAddress());
			for (Person person : persons) {
				CoverageDTO coverageDTO = new CoverageDTO();
				coverageDTO.setFirstName(person.getFirstName());
				coverageDTO.setLastname(person.getLastName());
				coverageDTO.setAddress(person.getAddress());
				coverageDTO.setPhone(person.getPhone());
				MedicalRecord medicalRecord = dataRepository
						.getMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
				int age = PersonUtils.getAge(medicalRecord.getBirthdate());
				if (age <= 18) {
					childCount++;
				} else {
					adultCount++;
				}
				coverageDTO.setNombreAdulte(adultCount);
				coverageDTO.setNombreEnfant(childCount);
				coverageDTOs.add(coverageDTO);
			}
		}

		return coverageDTOs;
	}

	@Override
	public List<FoyerDTO> getFoyerByFireStation(List<String> stationNumber) {
		List<FoyerDTO> foyerDTOs = new ArrayList<FoyerDTO>();
		List<String> listAddress = dataRepository.getListFireStation(stationNumber);
		for (String address : listAddress) {
			FoyerDTO foyerDTO = new FoyerDTO();
			List<FireDTO> fireDTOs = personService.getPersonByAddress(address);
			foyerDTO.setAddress(address);
			foyerDTO.setFirePerson(fireDTOs);
			foyerDTOs.add(foyerDTO);
		}
		return foyerDTOs;
	}


}
