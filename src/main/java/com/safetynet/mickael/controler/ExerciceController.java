package com.safetynet.mickael.controler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.service.IPersonService;

@RestController
public class ExerciceController {

	@Autowired
	private IPersonService personService;

	
	@GetMapping("communityEmail")
	public List<String> getCommunityEmail(@RequestParam String city){
		return personService.getCommunityEmail(city);
	}
	
}
