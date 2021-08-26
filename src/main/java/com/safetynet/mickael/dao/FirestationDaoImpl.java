package com.safetynet.mickael.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.mickael.model.Firestation;
import com.safetynet.mickael.repository.DataRepository;


@Service
public class FirestationDaoImpl implements FirestationDao{

	 @Autowired
	    private DataRepository dataRepository;

	    @Override
	    public boolean createFireStation(Firestation fireStation) {
	        // ajout de la nouvelle fireStation en memoire java
	        boolean result = dataRepository.getAllStation().add(fireStation);
	        // commit pour appliquer les changements dans le json
	        dataRepository.commit();
	        return result;
	    }

	    @Override
	    public boolean updateFireStation(Firestation fireStation) {
	        for (Firestation fs : dataRepository.getAllStation()) {

	            if (fs.getAddress().contentEquals(fireStation.getAddress())) {
	                boolean result = deleteFireStation(fs);
	                if (result) {
	                    result = createFireStation(fireStation);
	                    dataRepository.commit();
	                    return result;
	                }
	            }
	        }
	        return false;
	    }

	    @Override
	    public boolean deleteFireStation(Firestation fireStation) {
	        List<Firestation> fireStationListDeleted = new ArrayList<>();


	        // suppression by station et address
	        if (!"".equals(fireStation.getStation()) && (fireStation.getStation() != null) && (!"".equals(fireStation.getAddress()) && (fireStation.getAddress() != null))) {

	            for (Firestation fs : dataRepository.getDatabase().getFirestations()) {
	                if (fs.getStation().contentEquals(fireStation.getStation()) && (fs.getAddress().contentEquals(fireStation.getAddress()))) {
	                    fireStationListDeleted.add(fs);
	                }
	            }
	        } else {

	            // suppressiion by address uniquement
	            if (!"".equals(fireStation.getAddress()) && (fireStation.getAddress() != null)) {

	                for (Firestation fs : dataRepository.getDatabase().getFirestations()) {
	                    if (fs.getAddress().contentEquals(fireStation.getAddress())) {
	                        fireStationListDeleted.add(fs);
	                    }
	                }
	            }


	            // suppressiion by station uniquement

	            if (!"".equals(fireStation.getStation()) && (fireStation.getStation() != null)) {

	                for (Firestation fs : dataRepository.getDatabase().getFirestations()) {
	                    if (fs.getStation().contentEquals(fireStation.getStation())) {
	                        fireStationListDeleted.add(fs);
	                    }
	                }
	            }


	        }

	        // suppression de la fireStation en memoire
	        boolean result = dataRepository.getDatabase().getFirestations().removeAll(fireStationListDeleted);
	        // commit pour appliquer les changements dans le json
	        dataRepository.commit();
	        return result;

	    }
}
