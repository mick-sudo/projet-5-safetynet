package com.safetynet.mickael.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dao.MedicalRecordDao;
import com.safetynet.mickael.exception.DataAlreadyExistException;
import com.safetynet.mickael.exception.DataNotFoundException;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;
import com.safetynet.mickael.service.IMedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	@Autowired
	private DataRepository dataRepository;
	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@Override
	public void createMedicalRecord(MedicalRecord medicalRecord) {
		Person person = dataRepository.getPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (!dataRepository.getAllMedicalRecord().contains(medicalRecord) && (person != null)) {

			medicalRecordDao.createMedicalRecord(medicalRecord);
		} else {
			throw new DataAlreadyExistException("La personne n'existe pas ou le medicalRecord existe ");
		}
	}

	@Override
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		if (!medicalRecordDao.updateMedicalRecord(medicalRecord)) {
			throw new DataNotFoundException("La personne " + medicalRecord.getLastName() + " "
					+ medicalRecord.getFirstName() + " n'existe pas ");
		}
	}

	@Override
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		if (!medicalRecordDao.deleteMedicalRecord(medicalRecord)) {
			throw new DataNotFoundException("La personne " + medicalRecord.getLastName() + " "
					+ medicalRecord.getFirstName() + " n'a pas de dossier medical ");
		}
	}
}
