package com.safetynet.mickael.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.service.FirestationService;

@RestController
@RequestMapping("/stations")
public class FirestationControler {
	@Autowired
	private FirestationService firestationService;

	@GetMapping("/list")
	public Iterable<Firestation> list() {
		return firestationService.list();
	}

	@PostMapping("/save")
	public Firestation save(@RequestBody Firestation firestation) {
		return firestationService.save(firestation);
	}

	@PostMapping("/saveAll")
	public Iterable<Firestation> saveAll(@RequestBody List<Firestation> firestations) {
		return firestationService.save(firestations);
	}
}
