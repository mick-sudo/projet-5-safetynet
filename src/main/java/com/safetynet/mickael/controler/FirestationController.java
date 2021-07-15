package com.safetynet.mickael.controler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.FirestationDTO;
import com.safetynet.mickael.service.FirestationService;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
	
	@Autowired
	private FirestationService firestationService;

	@PostMapping("/save/firestation")
	public void save(@RequestBody FirestationDTO dto) {
		this.firestationService.save(dto);
	}
	
	@PostMapping("/save")
	public void save(@RequestBody Set<FirestationDTO> dtos) {
		this.firestationService.save(dtos);
	}
}
