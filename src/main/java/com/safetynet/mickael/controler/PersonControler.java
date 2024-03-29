package com.safetynet.mickael.controler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.FirestationDTO;
import com.safetynet.mickael.dto.PersonDTO;
import com.safetynet.mickael.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonControler {

	@Autowired
	private PersonService personService;

	@PostMapping("/save/person")
	public void save(@RequestBody PersonDTO dto) {
		this.personService.save(dto);
	}
	
	@PostMapping("/save")
	public void save(@RequestBody Set<PersonDTO> dtos) {
		this.personService.save(dtos);
	}
}
