package com.safetynet.mickael.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Firestation {
	@Id
	private String address;

	private String station;

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
