package com.safetynet.mickael.repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.mickael.exception.DataRepositoryException;
import com.safetynet.mickael.model.Database;
import com.safetynet.mickael.model.Person;

@Repository
public class DataRepository {

	// permet de mapper le json en objet java.
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static Database database;
	private String JSON_FILE = "data.json";
	private static final Logger logger = LogManager.getLogger(DataRepository.class);
	// on commit dans le main et non dans les tests
	private Boolean commit = true;

	public DataRepository() throws IOException {
		this.init();
	}

	public static Database getDatabase() {
		return database;
	}

	public static void setDatabase(Database database) {
		DataRepository.database = database;
	}

	public Boolean getCommit() {
		return commit;
	}

	public void setCommit(Boolean commit) {
		this.commit = commit;
	}

	public void init() {
		try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(JSON_FILE)) {
			database = objectMapper.readerFor(Database.class).readValue(inputStream);
			logger.info("OK-ouverture fichier json");
		} catch (FileNotFoundException fileNotFoundException) {
			logger.info("KO-fichier json introuvable");
			throw new DataRepositoryException("KO-fichier json introuvable" + JSON_FILE, fileNotFoundException);
		} catch (IOException ioException) {
			logger.info("KO-fichier corrompu");
			throw new DataRepositoryException("KO-fichier corrompu" + JSON_FILE, ioException);
		}
	}

	public void commit() {
		if (commit) {
			// recuperer le path du JSON
			URL url = ClassLoader.getSystemResource(JSON_FILE);
			// On ouvre un flux d'ecriture vers le fichier JSOn
			try (OutputStream outputStream = new FileOutputStream(url.getFile())) {
				objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, database);
				logger.info("OK - Fichier JSON mise à jour: " + JSON_FILE);

			} catch (FileNotFoundException fnfe) {

				logger.info("KO - FILE_NOT_FOUND : " + JSON_FILE);
				throw new DataRepositoryException("KO - FILE_NOT_FOUND : ", fnfe);

			} catch (IOException ioe) {

				logger.info("KO - I/O ERREUR : " + JSON_FILE);
				throw new DataRepositoryException("KO - I/O ERREUR : ", ioe);

			}
		}
	}

	public List<Person> getAllPerson() {
		return database.getPersons();
	}

	public List<Person> getPersonByAddress(String address) {
		return database.getPersons().stream().filter(person -> person.getAddress().equalsIgnoreCase(address))
				.collect(Collectors.toList());
	}

	public List<Person> getPersonByCity(String city) {
		return database.getPersons().stream().filter(person -> person.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
	}
	
	

}
