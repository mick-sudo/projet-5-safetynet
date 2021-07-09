package com.safetynet.mickael.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Allergie {
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
