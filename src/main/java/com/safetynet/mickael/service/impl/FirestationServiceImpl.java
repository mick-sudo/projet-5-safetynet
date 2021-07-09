package com.safetynet.mickael.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.repository.FirestationRepository;
import com.safetynet.mickael.service.FirestationService;

@Service
public class FirestationServiceImpl implements FirestationService{
	@Autowired
	private FirestationRepository firestationRepository;

	public List<Firestation> list() {
		return firestationRepository.findAll();
	}

	
	public Optional<Firestation> sf(String address) {
		return firestationRepository.findById(address);
	}

	
	public Firestation save(Firestation firestation) {
		return firestationRepository.save(firestation);
	}

	
	public Iterable<Firestation> save(List<Firestation> firestations) {
		return firestationRepository.saveAll(firestations);
	}


}
