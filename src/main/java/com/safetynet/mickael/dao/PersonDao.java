package com.safetynet.mickael.dao;

import com.safetynet.mickael.model.Person;

public interface PersonDao {

    boolean createPerson(Person person);

    boolean updatePerson(Person person);

    boolean deletePerson(Person person);
}
