package com.safetynet.mickael.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;
import com.safetynet.mickael.service.IPersonService;
import com.safetynet.mickael.utils.PersonUtils;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private DataRepository dataRepository;

	@Override
	public List<String> getCommunityEmail(String city) {
		List<String> emails = new ArrayList<String>();
		List<Person> persons = dataRepository.getPersonByCity(city);

		for (Person person : persons) {
			emails.add(person.getEmail());
		}
		return emails;
	}

	@Override
	public List<ChildAlertDTO> getChildAlert(String address) {
		List<ChildAlertDTO> childAlertDTOs = new ArrayList<ChildAlertDTO>();
		List<Person> persons = dataRepository.getPersonByAddress(address);

		for (Person person : persons) {
			MedicalRecord medicalRecord = dataRepository.getMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
			int age = PersonUtils.getAge(medicalRecord.getBirthdate());
			if (age <= 18) {
				ChildAlertDTO childAlertDTO = new ChildAlertDTO();
				childAlertDTO.setFistName(person.getFirstName());
				childAlertDTO.setLastName(person.getLastName());
				childAlertDTO.setAge(age);
				
				List<Person> familyMember = dataRepository.getFamilyMemberByLastName(person.getLastName());
				List<String> members = new ArrayList<String>();
				for (Person familyPerson : familyMember) {
					members.add(familyPerson.getFirstName());
				}
				childAlertDTO.setFamilyMember(members);
						
				childAlertDTOs.add(childAlertDTO);
			}
		}

		return childAlertDTOs;
	}

}
