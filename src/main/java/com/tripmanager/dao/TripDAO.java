package com.tripmanager.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmanager.model.Trip;

@Component
public class TripDAO {

	@Autowired
	private TripRepository tripRepository;

	public boolean save(Trip trip) {
		tripRepository.save(trip);
		return true;
	}

	public Trip findById(int id) {
		Optional<Trip> trip = tripRepository.findById(id);
		Trip nTrip;
		try {
			nTrip = trip.orElseThrow();
		} catch(Exception e) {
			throw e;
		}
		return nTrip;
	}
	
	public Trip findByName(String name) {
		return tripRepository.findByName(name);
	}


	public Iterable<Trip> findAll() {
		return tripRepository.findAll();
	}

	public void deleteById(int id) {
		tripRepository.deleteById(id);
	}

	

}
