package com.safetynet.mickael.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.safetynet.mickael.model.Person;
import com.safetynet.mickael.model.pk.PersonPk;

public interface PersonRepository extends JpaRepository<Person, PersonPk> {

	@Query(nativeQuery = true, value = "select p.*"
			+ " from firestation f inner join firestation_adresses fa on f.station = fa.firestation_station"
			+ "  inner join address a on fa.adresses_address = a.address"
			+ "  inner join person p on a.address = p.address_address"
			+ " where f.station = :stationNumber")
	Set<Person> findByFirestationNumber(Integer stationNumber);
	
	@Query(nativeQuery = true, value = " select *"
			+ " from person p"
			+ " where p.address_address = :address" )
	Set<Person> findbyAddress(String address);
}

