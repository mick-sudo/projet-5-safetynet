package com.safetynet.mickael.dto;

import java.util.Set;

public class FirestationPersonDTO {

	private int nbChildren;
	private int nbAdult;
	private Set<PersonDTO> persons;

	public int getNbChildren() {
		return nbChildren;
	}

	public void setNbChildren(int nbChildren) {
		this.nbChildren = nbChildren;
	}

	public int getNbAdult() {
		return nbAdult;
	}

	public void setNbAdult(int nbAdult) {
		this.nbAdult = nbAdult;
	}

	public Set<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonDTO> persons) {
		this.persons = persons;
	}

}
