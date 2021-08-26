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
	IPersonService personServiceTest;

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
		Assertions.assertThat(personServiceTest.createPerson(chris));

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
			Assertions.assertThat(personServiceTest.createPerson(chris));
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
		Assertions.assertThat(personServiceTest.updatePerson(chris));

		verify(personDao, Mockito.times(1)).updatePerson((chris));

	}

	@Test
	public void updateNoneExistingPersonTest() throws Exception {

		// when
		Mockito.when(personDao.updatePerson(any(Person.class))).thenReturn(false);

		// THEN
		// On crée un personne qui existe
		try {
			Assertions.assertThat(personServiceTest.updatePerson(chris));
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
		Assertions.assertThat(personServiceTest.deletePerson(chris));

		verify(personDao, Mockito.times(1)).deletePerson((chris));
	}

	@Test
	public void deleteNoneExistingPersonTest() throws Exception {

		// when
		Mockito.when(personDao.deletePerson(any(Person.class))).thenReturn(false);

		// THEN
		// On crée un personne qui existe
		try {
			Assertions.assertThat(personServiceTest.deletePerson(chris));
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
		Collection<String> emails = personServiceTest.getCommunityEmail(city);
		// then
		assertThat(emails).containsExactlyInAnyOrderElementsOf(List.of(chris.getEmail(), jill.getEmail()));
	}
	
}
