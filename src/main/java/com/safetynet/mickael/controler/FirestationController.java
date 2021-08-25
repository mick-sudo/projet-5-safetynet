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

import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.service.IFirestationService;

@RestController
public class FirestationController {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PersonControler.class);


    @Autowired
    private IFirestationService fireStationService;

    @PostMapping(path = "firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFireStation(@RequestBody @Valid Firestation firestation) {
        logger.info("createFireStation : appel du controller firestation");

        fireStationService.createFireStation(firestation);
    }


    @PutMapping(path = "firestation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFireStation(@RequestBody @Valid Firestation firestation) {
        logger.info("updateFireStation : appel du controller firestation");
        fireStationService.updateFireStation(firestation);
    }

    @DeleteMapping(path = "firestation")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteFireStation(@RequestBody @Valid Firestation firestation) {
        logger.info("deleteFireStation : appel du controller firestation");
        fireStationService.deleteFireStation(firestation);
    }
}
