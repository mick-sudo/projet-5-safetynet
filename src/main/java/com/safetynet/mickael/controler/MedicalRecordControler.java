package com.safetynet.mickael.controler;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.service.IMedicalRecordService;

@RestController
public class MedicalRecordControler {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PersonControler.class);


    @Autowired
    private IMedicalRecordService medicalRecordService;

    @PostMapping(path = "medicalRecord")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFireStation(@RequestBody @Valid MedicalRecord medicalRecord) {
        logger.info("createFireStation : appel du controller medicalRecord");
        medicalRecordService.createMedicalRecord(medicalRecord);
    }


    @PutMapping(path = "medicalRecord")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMedicalRecord(@RequestBody @Valid MedicalRecord medicalRecord) {
        logger.info("updateMedicalRecord : appel du controller medicalRecord");
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping(path = "medicalRecord")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteMedicalRecord(@RequestBody @Valid MedicalRecord firestation) {
        logger.info("deleteMedicalRecord : appel du controller medicalRecord");
        medicalRecordService.deleteMedicalRecord(firestation);
    }

}
