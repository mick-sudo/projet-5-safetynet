package com.safetynet.mickael.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.dto.FirestationDTO;
import com.safetynet.mickael.model.Address;
import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.repository.FirestationRepository;
import com.safetynet.mickael.service.FirestationService;

@Service
public class FirestationServiceImpl implements FirestationService {

	@Autowired
	private FirestationRepository firestationRepository;

	private Firestation convertFirestationDTOToFirestation(FirestationDTO dto) {
		// Verification de l existence de l entite
		Firestation entity = null;
		Optional<Firestation> optionalFirestation = this.firestationRepository
				.findById(Integer.parseInt(dto.getStation()));

		// si il existe
		if (optionalFirestation.isPresent()) {
			entity = optionalFirestation.get();

			// ajouter l adresse contenue dans le dto si pas encore presente
			if (!entity.getAdresses().stream().anyMatch(a -> a.getAddress().equals(dto.getAddress()))) {
				Address a = new Address();

				a.setAddress(dto.getAddress());
				entity.getAdresses().add(a);
			}
		} else {
			// creer le firestation
			entity = new Firestation();

			entity.setStation(Integer.parseInt(dto.getStation()));

			Set<Address> addresses = new HashSet<>();
			Address a = new Address();

			a.setAddress(dto.getAddress());
			addresses.add(a);
			entity.setAdresses(addresses);
		}

		return entity;
	}

	@Override
	public void save(FirestationDTO dto) {
		Firestation firestation = this.convertFirestationDTOToFirestation(dto);
		this.firestationRepository.save(firestation);
	}

	@Override
	public void save(Set<FirestationDTO> dtos) {
		Set<Firestation> firestations = new HashSet<>();

		for (FirestationDTO dto : dtos) {
			// Test si le station number contenu dans le dto
			// correspond a un station number contenu dans un element de la collection
			// firestations
			if (firestations.stream().anyMatch(f -> f.getStation().equals(Integer.parseInt(dto.getStation())))) {
				// Si correspondance
				// Alors recuperer l element
				Optional<Firestation> optionalFirestation = firestations.stream()
						.filter(f -> f.getStation().equals(Integer.parseInt(dto.getStation()))).findFirst();

				Firestation f = optionalFirestation.get();

				// Creer une nouvelle adresse et l attacher au firestation
				Address a = new Address();
				a.setAddress(dto.getAddress());
				f.getAdresses().add(a);
			} else {
				// Creer un nouveau firestation
				Firestation f = this.convertFirestationDTOToFirestation(dto);
				firestations.add(f);
			}
		}

		this.firestationRepository.saveAll(firestations);
	}

}
