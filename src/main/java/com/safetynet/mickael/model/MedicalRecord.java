package com.safetynet.mickael.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.safetynet.mickael.model.pk.PersonPk;

@Entity
@IdClass(PersonPk.class)
public class MedicalRecord {
	@Id
	private String firstName;

	@Id
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Person person;

	private String birthdate;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Medication> medications;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Allergie> allergies;

	public MedicalRecord(String firstName, String lastName) {
		super();
		this.setFirstName(firstName);
		this.lastName = lastName;
	}

	public MedicalRecord() {

	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
