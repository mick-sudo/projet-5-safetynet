package com.safetynet.mickael.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.safetynet.mickael.model.pk.PersonPk;

@Entity
@Table(name = "person")
@IdClass(PersonPk.class)
public class Person {
	@Id
	private String firstName;
	@Id
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name = "address", referencedColumnName = "address")
	@Column(insertable=false, updatable=false)
	private Firestation firestation;
	private String address;
	
	private String city;
	private String zip;
	private String phone;
	private String email;

	// constructeur par défaut
	public Person() {

	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Firestation getFirestation() {
		return firestation;
	}

	public void setFirestation(Firestation firestation) {
		this.firestation = firestation;
	}
}
