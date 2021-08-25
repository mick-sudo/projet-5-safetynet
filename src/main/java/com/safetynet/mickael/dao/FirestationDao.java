package com.safetynet.mickael.dao;

import com.safetynet.mickael.model.Firestation;

public interface FirestationDao {
	
	boolean createFireStation(Firestation fireStation);

	boolean updateFireStation(Firestation fireStation);

	boolean deleteFireStation(Firestation fireStation);
}
