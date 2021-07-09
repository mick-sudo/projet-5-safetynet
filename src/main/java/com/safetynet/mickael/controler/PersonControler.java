package com.safetynet.mickael.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonControler {
	@Autowired
	private PersonService personService;

	@GetMapping("/list")
	public Iterable<Person> list() {
		return personService.list();
	}

	@PostMapping("/save")
	public Person save(@RequestBody Person person) {
		return personService.save(person);
	}

	@PostMapping("/saveAll")
	public Iterable<Person> saveAll(@RequestBody List<Person> persons) {
		return personService.save(persons);
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityMail(@RequestParam String city) {
		return personService.getCommunityMail(city);
	}
}
