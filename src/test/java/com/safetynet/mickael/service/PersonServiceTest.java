package com.safetynet.mickael.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.mickael.dao.MedicalRecordDao;
import com.safetynet.mickael.dao.PersonDao;
import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.PersonInfoDTO;
import com.safetynet.mickael.exception.DataAlreadyExistException;
import com.safetynet.mickael.exception.DataNotFoundException;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {
	@Autowired
	IPersonService personService;

	@MockBean
	DataRepository dataRepository;

	@MockBean
	PersonDao personDao;

	@MockBean
	MedicalRecordDao medicalRecordDao;

	Person jill = new Person("jill", "valentine", "racoon", "Washinton DC", "12345", "54321", "jill@val.com");
	Person chris = new Person("chris", "redfield", "racoon", "californie", "78900", "34689", "chris@red.com");

	List<String> medication = List.of("doliprane,sirop");
	List<String> allergies = List.of("mais, gluten");

	String city = "racoon";

	@Test
	public void createNoneExistingNPersonTest() {

		// Given
		List<Person> persons = new ArrayList<>();

		// When
		Mockito.when(dataRepository.getAllPerson()).thenReturn(persons);

		// then
		Assertions.assertThat(personService.createPerson(chris));

		verify(personDao, Mockito.times(1)).createPerson((chris));

	}

	@Test
	public void createExistingNPersonTest() throws Exception {

		// Given
		List<Person> persons = new ArrayList<>();
		persons.add(chris);

		// When
		Mockito.when(dataRepository.getAllPerson()).thenReturn(persons);

		// then
		try {
			Assertions.assertThat(personService.createPerson(chris));
			verify(personDao, Mockito.times(0)).createPerson(any());
		} catch (DataAlreadyExistException eExp) {
			assert (eExp.getMessage().contains("existe déja"));
		}

	}

	@Test
	public void updateExistingPersonTest() throws Exception {

		// when
		Mockito.when(personDao.updatePerson(any(Person.class))).thenReturn(true);

		// then
		Assertions.assertThat(personService.updatePerson(chris));

		verify(personDao, Mockito.times(1)).updatePerson((chris));

	}

	@Test
	public void updateNoneExistingPersonTest() throws Exception {

		// when
		Mockito.when(personDao.updatePerson(any(Person.class))).thenReturn(false);

		// THEN
		// On crée un personne qui existe
		try {
			Assertions.assertThat(personService.updatePerson(chris));
			verify(personDao, Mockito.times(1)).updatePerson(any());
		} catch (DataNotFoundException eExp) {
			assert (eExp.getMessage().contains("n'existe pas"));
		}
	}

	@Test
	public void deleteExistingPersonTest() {
		// when
		Mockito.when(personDao.deletePerson(any(Person.class))).thenReturn(true);

		// then
		Assertions.assertThat(personService.deletePerson(chris));

		verify(personDao, Mockito.times(1)).deletePerson((chris));
	}

	@Test
	public void deleteNoneExistingPersonTest() throws Exception {

		// when
		Mockito.when(personDao.deletePerson(any(Person.class))).thenReturn(false);

		// THEN
		// On crée un personne qui existe
		try {
			Assertions.assertThat(personService.deletePerson(chris));
			verify(personDao, Mockito.times(1)).deletePerson(any());
		} catch (DataNotFoundException eExp) {
			assert (eExp.getMessage().contains("n'existe pas"));
		}
	}

	@Test
	public void getValidCommunityEmailTest() throws Exception {

		// Given
		Mockito.when(dataRepository.getPersonByCity(city)).thenReturn(List.of(chris, jill));
		// when
		Collection<String> emails = personService.getCommunityEmail(city);
		// then
		assertThat(emails).containsExactlyInAnyOrderElementsOf(List.of(chris.getEmail(), jill.getEmail()));
	}
	
	@Test
	public void getChildAlertTest() throws Exception {
		// Préparation du jeu de tests
		List<Person> persons = new ArrayList<Person>();
		persons.add(chris);
		Mockito.when(dataRepository.getPersonByAddress("californie")).thenReturn(persons);
		
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setAllergies(allergies);
		medicalRecord.setMedications(medication);
		medicalRecord.setFirstName("chris");
		medicalRecord.setLastName("redfield");
		medicalRecord.setBirthdate("28/05/2010");
		Mockito.when(dataRepository.getMedicalRecordByFirstNameAndLastName("chris","redfield")).thenReturn(medicalRecord);

		List<Person> familyMember = new ArrayList<Person>();
		Mockito.when(dataRepository.getFamilyMemberByLastName("redfield")).thenReturn(familyMember);
		
		// Appel de la méthode à tester
		List<ChildAlertDTO> result = personService.getChildAlert("californie");
		
		// Vérifier les données du résultat
		org.junit.jupiter.api.Assertions.assertEquals(result.size(), 1);
		
		ChildAlertDTO childAlertDTO = result.get(0);
		org.junit.jupiter.api.Assertions.assertEquals(childAlertDTO.getAge(), 11);
		org.junit.jupiter.api.Assertions.assertEquals(childAlertDTO.getFistName(), "chris");
		org.junit.jupiter.api.Assertions.assertEquals(childAlertDTO.getLastName(),"redfield");
		org.junit.jupiter.api.Assertions.assertEquals(childAlertDTO.getFamilyMember().size(), 0);
		
	}
}
