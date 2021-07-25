package com.safetynet.mickael.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.FireAddressDTO;
import com.safetynet.mickael.service.PersonService;

@RestController
public class ExerciceController {

	@Autowired
	private PersonService personService;

	@GetMapping("/fire")
	public List<FireAddressDTO> findFireByAddress(@RequestParam String address) {
		return this.personService.findFireByAdress(address);
	}
}
