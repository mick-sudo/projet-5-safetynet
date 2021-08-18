package com.safetynet.mickael.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.repository.DataRepository;

@Service
public class MedicalRecordDaoImpl implements MedicalRecordDao {

	@Autowired
	private DataRepository dataRepository;

	@Override
	public boolean createMedicalRecord(MedicalRecord medicalRecord) {
		dataRepository.getAllMedicalRecord().add(medicalRecord);
		dataRepository.commit();
		return true;
	}

	@Override
	public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
		for (MedicalRecord mr : dataRepository.getAllMedicalRecord()) {
			if (mr.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
					&& (mr.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))) {
				boolean result = deleteMedicalRecord(mr);
				if (result) {
					result = createMedicalRecord(medicalRecord);
					dataRepository.commit();
					return result;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		boolean result = dataRepository.getAllMedicalRecord().remove(medicalRecord);
		dataRepository.commit();
		return result;
	}
}
