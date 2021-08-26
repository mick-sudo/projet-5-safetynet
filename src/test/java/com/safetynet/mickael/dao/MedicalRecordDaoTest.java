package com.safetynet.mickael.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.repository.DataRepository;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MedicalRecordDaoTest {

	 List<String> medications = new ArrayList<String>(
	            Arrays.asList("a", "b", "c", "d"));
	    List<String> allergies = new ArrayList<String>(
	            Arrays.asList("e", "f", "g", "h"));

	    MedicalRecord medicalrecord = new MedicalRecord("Roqsdqsgzazeer", "Boyd", "03/06/1984", medications, allergies);

	    @Autowired
	    MedicalRecordDaoImpl medicalRecordDao;

	    @Autowired
	    DataRepository dataRepository;

	    @BeforeEach
	    void init(){
	        dataRepository.init();
	        dataRepository.setCommit(false);
	    }

	    @Test
	    void createMedicalRecord() {

	        assertThat(medicalRecordDao.createMedicalRecord(medicalrecord)).isTrue();
	        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isTrue();
	    }

	    @Test
	    void updateMedicalRecord() {

	        assertThat(medicalRecordDao.updateMedicalRecord(medicalrecord)).isFalse();
	        medicalRecordDao.createMedicalRecord(medicalrecord);
	        assertThat(medicalRecordDao.updateMedicalRecord(medicalrecord)).isTrue();
	    }


	    @Test
	    void deleteMedicalRecord() {

	        assertThat(medicalRecordDao.createMedicalRecord(medicalrecord)).isTrue();
	        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isTrue();
	        assertThat(medicalRecordDao.deleteMedicalRecord(medicalrecord)).isTrue();
	        assertThat(dataRepository.getAllMedicalRecord().contains(medicalrecord)).isFalse();
	    }
}
