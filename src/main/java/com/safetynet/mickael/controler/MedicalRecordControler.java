package com.safetynet.mickael.controler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.dto.MedicalRecordDTO;
import com.safetynet.mickael.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordControler {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@PostMapping("/save/medicalrecord")
	public void save(@RequestBody MedicalRecordDTO dto) {
		this.medicalRecordService.save(dto);
	}

	@PostMapping("/save")
	public void save(@RequestBody Set<MedicalRecordDTO> dtos) {
		this.medicalRecordService.save(dtos);
	}
}
