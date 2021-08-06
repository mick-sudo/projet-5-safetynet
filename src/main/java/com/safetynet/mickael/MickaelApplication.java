package com.safetynet.mickael;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.mickael.repository.DataRepository;

@SpringBootApplication
public class MickaelApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MickaelApplication.class, args);
		DataRepository dataRepository = new DataRepository();
		System.out.println(dataRepository.getDatabase().getPersons().get(0).getFirstName());
	}

}
