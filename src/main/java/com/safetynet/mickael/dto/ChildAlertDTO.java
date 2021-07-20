package com.safetynet.mickael.dto;

import java.util.Set;

public class ChildAlertDTO {

	private Set<PersonDTO> childrens;
	private Set<PersonDTO> adults;

	public Set<PersonDTO> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<PersonDTO> childrens) {
		this.childrens = childrens;
	}

	public Set<PersonDTO> getAdults() {
		return adults;
	}

	public void setAdults(Set<PersonDTO> adults) {
		this.adults = adults;
	}

}
