package com.safetynet.mickael.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.safetynet.mickael.model.pk.PersonPk;

@Entity
@IdClass(PersonPk.class)
public class MedicalRecord {
	@Id
	private String firstName;
	@Id
	private String lastName;
	@OneToOne
	private Person person;

	private String birthdate;

	@ManyToMany
	private Set<Medication> medications;

	@ManyToMany
	private Set<Allergie> allergies;

	public MedicalRecord(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Set<Medication> getMedications() {
		return medications;
	}

	public void setMedications(Set<Medication> medications) {
		this.medications = medications;
	}

	public Set<Allergie> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergie> allergies) {
		this.allergies = allergies;
	}
}
