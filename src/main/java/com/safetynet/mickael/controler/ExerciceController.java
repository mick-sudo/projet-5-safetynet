package com.safetynet.mickael.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.ChildAlertDTO;
import com.safetynet.mickael.dto.FirestationPersonDTO;
import com.safetynet.mickael.service.PersonService;

@RestController
public class ExerciceController {

	@Autowired
	private PersonService personService;

	@GetMapping("/firestation")
	public FirestationPersonDTO findPersonByFirestationNumner(@RequestParam Integer stationNumber) {
		return this.personService.findByFirestationNumber(stationNumber);
	}
	
	@GetMapping("/childAlert")
	public ChildAlertDTO findChildByAddress(@RequestParam String address) {
		return this.personService.findbyAddress(address);
	}
}
