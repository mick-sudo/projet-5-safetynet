package com.safetynet.mickael.model.pk;

import java.io.Serializable;

public class PersonPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6410097078064322253L;
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
