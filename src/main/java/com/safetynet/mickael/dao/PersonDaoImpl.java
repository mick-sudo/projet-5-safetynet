package com.safetynet.mickael.dao;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.repository.DataRepository;

@Service
public class PersonDaoImpl implements PersonDao{
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PersonDaoImpl.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public boolean createPerson(Person person) {
        // ajout de la nouvelle person en memoire java
        dataRepository.getAllPerson().add(person);
        logger.info("createPerson : person ajouter a la liste des persons");
        // commit pour appliquer les changements dans le json
        dataRepository.commit();
        logger.info("createperson: person ajouter");
        return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        if (dataRepository.getAllPerson().remove(person)) {
            this.createPerson(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePerson(Person person) {
        // suppression de la person en memeoire
        boolean result = dataRepository.getAllPerson().remove(person);
        // commit pour appliquer les changements dans le json
        dataRepository.commit();
        logger.info("deleteperson: person supprimé");
        return result;

    }
}
