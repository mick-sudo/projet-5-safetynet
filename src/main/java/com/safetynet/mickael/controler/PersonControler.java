package com.safetynet.mickael.controler;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.service.IPersonService;

@RestController
public class PersonControler {
	@Autowired
	private IPersonService personService;

	@PostMapping(path = "person")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPerson(@RequestBody @Valid Person person) {

		personService.createPerson(person);
	}

	@PutMapping(path = "person")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePerson(@RequestBody @Valid Person person) {

		personService.updatePerson(person);
	}

	@DeleteMapping(path = "person")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deletePerson(@RequestBody @Valid Person person) {

		personService.deletePerson(person);
	}

}
