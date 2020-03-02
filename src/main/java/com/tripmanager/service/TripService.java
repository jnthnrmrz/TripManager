package com.tripmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmanager.TripNotFoundException;
import com.tripmanager.dao.TripDAO;
import com.tripmanager.model.Trip;

@Component
public class TripService {
	
	@Autowired
	private TripDAO tripdao;

	/**
	 * All trip information
	 * @return An Iterable of type Trip
	 */
	public Iterable<Trip> getAllTrips() {
		return  tripdao.findAll();
	}

	/**
	 * Find a trip by name
	 * @param name of trip
	 * @return The whole trip details
	 */
	public Trip getTrip(String name) {
		Trip trip = tripdao.findByName(name);
		if(trip == null) {
			throw new TripNotFoundException();
		}
		return trip;
	}
	
	/**
	 * Find a trip by id
	 * @param id
	 * @return
	 */
	public Trip getTrip(int id) {
		Trip trip;
		try {
			trip = tripdao.findById(id);
		} catch(Exception e) {
			return null;
		}
		return trip;
	}

	/**
	 * Add a trip to database
	 * @param trip
	 */
	public void addTrip(Trip trip) {
		tripdao.save(trip);
	}
	
	/**
	 * Deletes a trip based on id
	 * @param id
	 */
	public void deleteTrip(int id) {
		tripdao.deleteById(id);
		//TODO: Should delete all relative trip events
	}
}
