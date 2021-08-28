package com.safetynet.mickael.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.mickael.dao.FirestationDaoImpl;
import com.safetynet.mickael.dto.FireDTO;
import com.safetynet.mickael.dto.FoyerDTO;
import com.safetynet.mickael.exception.DataAlreadyExistException;
import com.safetynet.mickael.exception.DataNotFoundException;
import com.safetynet.mickael.exception.InvalidArgumentException;
import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.model.MedicalRecord;
import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FirestationServiceTest {

    @Autowired
    IFirestationService fireStationService;
    
    @MockBean
    IPersonService personService;

    @MockBean
    FirestationDaoImpl firestationDaoMock;

    @MockBean
    DataRepository dataRepository;

    @MockBean
    DataNotFoundException dataNotFoundException;

    @MockBean
    InvalidArgumentException invalidArgumentException;

    Firestation firestation1 = new Firestation("WhiteHouse", "1");
    Firestation firestation2 = new Firestation("BlackHouse", "2");
    Firestation firestationVide = new Firestation("", "");

    Person obama = new Person("Barack", "obama", "WhiteHouse", "Washinton DC", "1232111","06755" , "obama@mohamed.com");
    Person biden = new Person("joe", "biden", "BlackHouse", "Washinton DC", "1232111","06754" , "biden@mohamed.com");
    Person trump = new Person("Donald", "trump", "CasinoHouse", "Washinton DC", "1232111", "06753","trump@mohamed.com");
   
    List<String> medication = List.of("a,b,c,d");
    List<String> allergies = List.of("q,s,d,d");

    MedicalRecord medicalrecordObama = new MedicalRecord("Barack", "obama",
            "03/06/1984", medication, allergies);


    @Test
    public void createExistingFirestationTest() throws Exception {

        // GIVEN
        List<Firestation> listFirestation = new ArrayList<Firestation>();
        listFirestation.add(firestation1);

        // WHEN
        Mockito.when(dataRepository.getAllStation()).thenReturn(listFirestation);

        // THEN
        // On crée une station qui existe
        try {
            Assertions.assertFalse(fireStationService.createFireStation(firestation1));
            verify(firestationDaoMock, Mockito.times(0)).createFireStation(firestation1);
        } catch (DataAlreadyExistException eExp) {
            assert (eExp.getMessage().contains("existe déja"));
        }
    }

    @Test
    public void createInvalidFirestationTest() throws Exception {

        // GIVEN
        List<Firestation> listFirestation = new ArrayList<Firestation>();
        listFirestation.add(firestationVide);

        // WHEN
        Mockito.when(dataRepository.getAllStation()).thenReturn(listFirestation);

        // THEN
        // On crée une station qui existe
        try {
            Assertions.assertFalse(fireStationService.createFireStation(firestationVide));
            verify(firestationDaoMock, Mockito.times(0)).createFireStation(firestationVide);
        } catch (InvalidArgumentException eExp) {
            assert (eExp.getMessage().contains("adresse ou station vide"));
        }
    }


    @Test
    public void createValidFirestationTest() throws Exception {

        //given
        List<Firestation> listFirestation = new ArrayList<Firestation>();
        //when
        Mockito.when(dataRepository.getAllStation()).thenReturn(listFirestation);

        //then
        Assertions.assertTrue(fireStationService.createFireStation(firestation1));
        verify(firestationDaoMock, Mockito.times(1)).createFireStation(firestation1);

    }

    @Test
    public void updateExistingFirestationTest() throws Exception {
        // GIVEN

        // WHEN

        Mockito.when(
                firestationDaoMock. updateFireStation(any(Firestation.class)))
                .thenReturn(true);

        // THEN
        // On MAJ la station
        Assertions.assertTrue(
                fireStationService.updateFireStation(firestation1));
        verify(firestationDaoMock, Mockito.times(1))
                .updateFireStation(firestation1);

    }

    @Test
    public void updateNonExistingFirestationTest() throws Exception {
        // GIVEN
        // WHEN
        Mockito.when(firestationDaoMock.updateFireStation(any(Firestation.class))).thenReturn(false);
        // THEN
        // On crée une station qui existe
        try {
            Assertions.assertFalse(fireStationService.updateFireStation(firestation1));
            verify(firestationDaoMock, Mockito.times(1)).updateFireStation(firestation1);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }
    }

    @Test
    public void deleteExistingFirestationTest() throws Exception {

        Mockito.when(
                firestationDaoMock.deleteFireStation(any(Firestation.class)))
                .thenReturn(true);

        // THEN
        // On MAJ la station
        Assertions.assertTrue(
                fireStationService.deleteFireStation(firestation1));
        verify(firestationDaoMock, Mockito.times(1))
                .deleteFireStation(firestation1);


    }

    @Test
    public void deleteNonExistingFirestationTest() throws Exception {

        // GIVEN
        // WHEN
        Mockito.when(firestationDaoMock.deleteFireStation(any(Firestation.class))).thenReturn(false);
        // THEN
        // On crée une station qui existe
        try {
            Assertions.assertFalse(fireStationService.deleteFireStation(firestation1));
            verify(firestationDaoMock, Mockito.times(1)).deleteFireStation(firestation1);
        } catch (DataNotFoundException dnfe) {
            assert (dnfe.getMessage().contains("n'existe pas"));
        }
    }
    
    @Test
    public void getPhoneByStationTest() {
    	List<Person> persons = new ArrayList<Person>();
    	persons.add(biden);
    	persons.add(obama);
    	persons.add(trump);
    	List<Firestation> firestations= new ArrayList<>();
    	firestations.add(firestation1);
    	Mockito.when(dataRepository.getAllPerson()).thenReturn(persons);
    	Mockito.when(dataRepository.getFirestationByStation("1")).thenReturn(firestations);
    	List<String> phones =  fireStationService.getPhoneByStation("1");
    	Assertions.assertEquals(phones.size(), 1);
    	Assertions.assertEquals(phones.get(0), persons.get(1).getPhone());
    	
    	
    }
    
    @Test
    public void getFoyerByFireStationTest() {
    	List<String> addreList = new ArrayList<String>();
    	addreList.add("WhiteHouse");
    	Mockito.when(dataRepository.getListFireStation(List.of("1"))).thenReturn(addreList);
    	FireDTO fireDTO = new FireDTO();
    	fireDTO.setAge(9);
    	fireDTO.setFirstName("chris");
    	fireDTO.setLastName("redfield");
    	fireDTO.setAllergies(allergies);
    	fireDTO.setMedications(medication);
    	fireDTO.setPhone("1234");
    	fireDTO.setStation("1");
    	List<FireDTO> fireDTOs = new ArrayList<FireDTO>();
    	fireDTOs.add(fireDTO);
    	Mockito.when(personService.getPersonByAddress("WhiteHouse")).thenReturn(fireDTOs);
    	
    	List<FoyerDTO> result = fireStationService.getFoyerByFireStation(List.of("1"));
    	
    	org.junit.jupiter.api.Assertions.assertEquals(result.size(), 1);
    	FoyerDTO foyerDTO = result.get(0);
    	org.junit.jupiter.api.Assertions.assertEquals(foyerDTO.getAddress(), "WhiteHouse");
    	org.junit.jupiter.api.Assertions.assertEquals(foyerDTO.getFirePerson(), fireDTOs);
    }
    
}
