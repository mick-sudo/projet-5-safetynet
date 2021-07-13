package com.safetynet.mickael.service;

import java.util.List;

import com.safetynet.mickael.model.Firestation;

public interface FirestationService {

	Iterable<Firestation> list();
	Firestation save(Firestation firestation);
	Iterable<Firestation> save(List<Firestation> firestations);

	
}
