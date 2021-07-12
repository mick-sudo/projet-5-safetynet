package com.safetynet.mickael.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordControler {
@Autowired
private MedicalRecordService medicalRecordService;

@GetMapping("/list")
public Iterable<MedicalRecord> list() {
	return medicalRecordService.list();
}

@PostMapping("/save")
public MedicalRecord save(@RequestBody MedicalRecord medicalRecord) {
	return medicalRecordService.save(medicalRecord);
}

@PostMapping("/saveAll")
public Iterable<MedicalRecord> saveAll(@RequestBody List<MedicalRecord> medicalRecords) {
	return medicalRecordService.save(medicalRecords);
}
}
