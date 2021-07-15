package com.safetynet.mickael.controler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.BddDTO;
import com.safetynet.mickael.dto.FirestationDTO;
import com.safetynet.mickael.service.BddService;

@RestController
@RequestMapping("/bdd")
public class BddControler {
	
	@Autowired
	private BddService bddService;
	
	@PostMapping("/save")
	public void save(@RequestBody BddDTO dto) {
		this.bddService.save(dto);
	}
}
