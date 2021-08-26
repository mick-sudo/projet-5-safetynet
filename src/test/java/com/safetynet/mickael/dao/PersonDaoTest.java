package com.safetynet.mickael.dao;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonDaoTest {

	 Person jill = new Person("jill", "valentine", "racoon", "Washinton DC", "12345","54321" , "jill@val.com");
	 @Autowired
	    PersonDaoImpl personDao;

	    @Autowired
	    DataRepository dataRepository;

	    @BeforeEach
	    void init(){
	        dataRepository.init();
	        dataRepository.setCommit(false);
	    }

	    @Test
	    void createPerson() {
	        Assertions.assertFalse(dataRepository.getAllPerson().contains(jill));
	        assertThat(personDao.createPerson(jill)).isTrue();
	        assertThat(dataRepository.getAllPerson().contains(jill)).isTrue();
	    }
	    
	    @Test
	    void updatePerson() {
	        assertThat(personDao.updatePerson(jill)).isFalse();
	        personDao.createPerson(jill);
	        assertThat(personDao.updatePerson(jill)).isTrue();
	    }


	    @Test
	    void deletePerson() {
	        assertThat(personDao.deletePerson(jill)).isFalse();
	        assertThat(personDao.createPerson(jill)).isTrue();
	        assertThat(dataRepository.getAllPerson().contains(jill)).isTrue();
	        assertThat(personDao.deletePerson(jill)).isTrue();
	        assertThat(dataRepository.getAllPerson().contains(jill)).isFalse();
	    }
}
