package com.safetynet.mickael.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.safetynet.mickael.controler.FirestationController;
import com.safetynet.mickael.controler.PersonControler;
import com.safetynet.mickael.service.IFirestationService;
import com.safetynet.mickael.service.IPersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExerciceControllerTest {

	@Mock
	PersonControler personControler = new PersonControler();

	@Mock
	FirestationController fireStationController = new FirestationController();

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IPersonService iPersonService;

	@MockBean
	IFirestationService firestationService;

	@Test
	void getCommunityEmail() throws Exception {
		// given
		List<String> listEmails = List.of("ja@a.com", "b@b.com", "c@C.fr");

		// etape 1 on moque le comportement de ipersonService pour envoyer des valeur
		// d'email
		Mockito.when(iPersonService.getCommunityEmail("Culver")).thenReturn(listEmails);

		// etape2 on envoie une reque GET avec param Culver comme value un 200 isOk()
		mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail").param("city", "Culver"))
				.andExpect(MockMvcResultMatchers.status().isOk()); 
		// etap 3 on verifie que le serivce a bien été appele avec le bon parametre

		Mockito.verify(iPersonService, Mockito.times(1)).getCommunityEmail("Culver");
	}

	@Test
	void getChildByAddress() throws Exception {

        // Test 1 : on envoie une requête GET avec des parametre
        // pour verifier si le statut 200 est ok

		mockMvc.perform(MockMvcRequestBuilders
				.get("/childAlert").param("address", "1509 Culver St"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Test 2 : on vérifie que le service a bien été appelé avec les bons
		// paramètres

		Mockito.verify(iPersonService, Mockito.times(1)).getChildAlert("1509 Culver St");

		// Test 3 : on envoie une requête GET avec en paramètre une adresse
		// non valide
		// + on vérifie que le retour est vide

		mockMvc.perform(MockMvcRequestBuilders.get("/childAlert").param("address", "999 Culver St"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));

		// Test 4 : on envoie une requête GET avec en paramètre une adresse
		// sans enfants
		// + on vérifie que le retour est vide

		mockMvc.perform(MockMvcRequestBuilders.get("/childAlert").param("address", "908 73rd St"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	@Test
	void getPersonInfo() throws Exception {

        // Test 1 : on envoie une requête GET avec des parametre
        // pour verifier si le statut 200 est ok

		mockMvc.perform(
				MockMvcRequestBuilders.get("/personInfo").param("lastName", "Boyd").param("firstName", "george"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Etape 2 : on vérifie que le service a bien été appelé avec les bons
		// paramètres

		Mockito.verify(iPersonService, Mockito.times(1)).getpersonInfo("george", "Boyd");
	}

	@Test
	void getPhoneAlert() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/phoneAlert")
				.param("firestation", "1")

		).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(firestationService, Mockito.times(1)).getPhoneByStation("1");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
                .param("firestation", "0"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	@Test
	void getCoverageByFireStation() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
				.get("/firestation")
				.param("stationNumber", "1")
				).andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(firestationService, Mockito.times(1)).getCoverageByFireStation("1");
	}
	
	 	@Test
	    void getFoyerByFireStations() throws Exception {

	        List<String> stations = Arrays.asList("1", "2");

	        // Test 1 : on envoie une requête GET avec des parametre
	        // pour verifier si le statut 200 est ok

	        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations")
	                .param("stations", "1", "2"))
	                .andExpect(MockMvcResultMatchers.status().isOk());

	        // Test 2 : on vérifie que le service a bien été appelé avec les bons
	        // paramètres

	        Mockito.verify(firestationService, Mockito.times(1))
	                .getFoyerByFireStation(stations);

	        // Test 3 : on envoie une requête GET avec en paramètre une station
	        // qui n'existe pas
	        // + on vérifie que le retour est vide

	        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations")
	                .param("stations", "0"))
	                .andExpect(MockMvcResultMatchers.content().string("[]"));
	    }
	 	
	 	@Test
	    void getPersonByAddress() throws Exception{
	 		mockMvc.perform(MockMvcRequestBuilders
					.get("/fire")
					.param("address", "1509 Culver St")
					).andExpect(MockMvcResultMatchers.status().isOk());
	 		
	 		Mockito.verify(iPersonService, Mockito.times(1))
	 		.getPersonByAddress("1509 Culver St");
	 		
	        mockMvc.perform(MockMvcRequestBuilders.get("/fire")
	                .param("address", "0 rue blabla"))
	                .andExpect(MockMvcResultMatchers.content().string("[]"));
	 	}

}
