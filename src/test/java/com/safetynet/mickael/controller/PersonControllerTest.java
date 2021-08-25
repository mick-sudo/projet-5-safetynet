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
import com.safetynet.mickael.controler.PersonControler;
import com.safetynet.mickael.exception.DataAlreadyExistException;
import com.safetynet.mickael.service.IPersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Mock
	PersonControler personControler = new PersonControler();

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IPersonService iPersonService;

	String firstName = "max";
	String lastName = "ulrich";
	String address = "124 rue de l'empire";
	String city = "berlin";
	String zip = "56943";
	String phone = "567-874-6513";
	String email = "max@email.com";

	@Test
	void createPersonValid() throws Exception {
		// given
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode jsonPerson = objectMapper.createObjectNode();
		jsonPerson.set("firstName", TextNode.valueOf(firstName));
		jsonPerson.set("lastName", TextNode.valueOf(lastName));
		// when

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/person") // requete
				.contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
				.andExpect(MockMvcResultMatchers.status().isCreated()); // test
	}

	@Test
	void createPersonInvalid() throws Exception {
		// given

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode jsonPerson = objectMapper.createObjectNode();
		jsonPerson.set("firstName", TextNode.valueOf(firstName));
		jsonPerson.set("lastName", TextNode.valueOf(""));
		// when

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/person") // requete
				.contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
				.andExpect(MockMvcResultMatchers.status().isBadRequest()); // test
	}

	@Test
	void createPersonAlreadyExist() throws Exception {
		// given
		Mockito.doThrow(DataAlreadyExistException.class).when(iPersonService).createPerson(Mockito.any());
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode jsonPerson = objectMapper.createObjectNode();
		jsonPerson.set("firstName", TextNode.valueOf(firstName));
		jsonPerson.set("lastName", TextNode.valueOf("lastName"));

		// when

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/person") // requete
				.contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
				.andExpect(MockMvcResultMatchers.status().isConflict()); // test
	}
	
	 @Test
	    void updatePersonValid() throws Exception {
	        //given
	        ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode jsonPerson = objectMapper.createObjectNode();
	        jsonPerson.set("firstName", TextNode.valueOf(firstName));
	        jsonPerson.set("lastName", TextNode.valueOf(lastName));
	        //when

	        //then
	        mockMvc.perform(MockMvcRequestBuilders.put("/person")      //requete
	                .contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
	                .andExpect(MockMvcResultMatchers.status().isNoContent()); // test
	    }

	    @Test
	    void updatePersonInvalid() throws Exception {
	        //given
	        ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode jsonPerson = objectMapper.createObjectNode();
	        jsonPerson.set("firstName", TextNode.valueOf(""));
	        jsonPerson.set("lasName", TextNode.valueOf(""));
	        //when

	        //then
	        mockMvc.perform(MockMvcRequestBuilders.put("/person")      //requete
	                .contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
	                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // test
	    }


	    @Test
	    void deletePersonValid() throws Exception {
	        //given
	        ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode jsonPerson = objectMapper.createObjectNode();
	        jsonPerson.set("firstName", TextNode.valueOf(firstName));
	        jsonPerson.set("lastName", TextNode.valueOf(lastName));
	        //when

	        //then
	        mockMvc.perform(MockMvcRequestBuilders.delete("/person")      //requete
	                .contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
	                .andExpect(MockMvcResultMatchers.status().isResetContent()); // test
	    }

	    @Test
	    void deletePersonInvalid() throws Exception {
	        //given
	        // on mock IPersonService on lui dit de renvoyer l'exception DataAlreadyExist uniquement quand on lui demande de creer une personne
	        ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode jsonPerson = objectMapper.createObjectNode();
	        jsonPerson.set("firstName", TextNode.valueOf(""));
	        jsonPerson.set("lastName", TextNode.valueOf(""));
	        //when
	        //then
	        mockMvc.perform(MockMvcRequestBuilders.delete("/person")      //requete
	                .contentType(MediaType.APPLICATION_JSON).content(jsonPerson.toString())) // contenu
	                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // test
	    }

	    

}
