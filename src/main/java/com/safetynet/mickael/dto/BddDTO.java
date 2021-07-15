package com.safetynet.mickael.dto;

import java.util.Set;

public class BddDTO {

	private Set<PersonDTO> persons;
	private Set<FirestationDTO> firestations;
	private Set<MedicalRecordDTO> medicalrecords;

	public Set<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonDTO> persons) {
		this.persons = persons;
	}

	public Set<FirestationDTO> getFirestations() {
		return firestations;
	}

	public void setFirestations(Set<FirestationDTO> firestations) {
		this.firestations = firestations;
	}

	public Set<MedicalRecordDTO> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(Set<MedicalRecordDTO> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

}
