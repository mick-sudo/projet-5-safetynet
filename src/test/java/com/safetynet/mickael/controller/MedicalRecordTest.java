package com.safetynet.mickael.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.safetynet.mickael.exception.DataAlreadyExistException;
import com.safetynet.mickael.exception.DataNotFoundException;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.service.IMedicalRecordService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordTest {

	@Mock
	MedicalRecord medicalRecord = new MedicalRecord();
	
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    IMedicalRecordService medicalrecordService;
    
    String firstName = "rick";
    String lastName = "Math";
    String birthdate = "10/05/2001";
    
    @Test
    void createMedicalRecordValid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalRecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalRecord.set("firstName", TextNode.valueOf(firstName));
        jsonMedicalRecord.set("lastName", TextNode.valueOf(lastName));
        jsonMedicalRecord.set("birthdate", TextNode.valueOf(birthdate));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalRecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    void createFireStationInvalid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalRecord = obm.createObjectNode();

        //GIven
        jsonMedicalRecord.set("firstName", TextNode.valueOf(""));
        jsonMedicalRecord.set("lastName", TextNode.valueOf(lastName));


        //When
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalRecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    void createMedicalrecordWhenMedicalrecordAlreadyExist() throws Exception {

        // on mock MedicalrecordService et on lui dit de renvoyer l'exception
        // DataALreadExist
        // quand on lui demande de renvoyer un Medicalrecord existant

        Mockito.doThrow(DataAlreadyExistException.class)
                .when(medicalrecordService).createMedicalRecord(Mockito.any());

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstName));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastName));

        // WHEN
        // THEN

        mockMvc.perform(MockMvcRequestBuilders.post("/Medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalrecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // --------------- MAJ DE DOSSIERS MEDICAUX -----------------

    @Test
    void updateMedicalrecordValid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalrecord.set("firstName", TextNode.valueOf("John"));
        jsonMedicalrecord.set("lastName", TextNode.valueOf("Boyd"));
        jsonMedicalrecord.set("birthdate", TextNode.valueOf(birthdate));

        // WHEN
        // THEN

        mockMvc.perform(MockMvcRequestBuilders.put("/Medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalrecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateMedicalrecordInvalid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalrecord.set("firstName", TextNode.valueOf(""));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(""));

        // WHEN
        // THEN

        mockMvc.perform(MockMvcRequestBuilders.put("/Medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalrecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateMedicalrecordWhenMedicalrecordNotFound() throws Exception {

        Mockito.doThrow(DataNotFoundException.class).when(medicalrecordService)
                .updateMedicalRecord(Mockito.any());

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstName));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastName));

        // WHEN
        // THEN

        mockMvc.perform(MockMvcRequestBuilders.put("/Medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalrecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteFireStationValid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonMedicalrecord = obm.createObjectNode();

        // GIVEN

        jsonMedicalrecord.set("firstName", TextNode.valueOf(firstName));
        jsonMedicalrecord.set("lastName", TextNode.valueOf(lastName));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMedicalrecord.toString()))
                .andExpect(MockMvcResultMatchers.status().isResetContent());
    }

 
}
