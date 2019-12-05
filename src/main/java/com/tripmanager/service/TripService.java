package com.tripmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmanager.TripNotFoundException;
import com.tripmanager.dao.TripRepository;
import com.tripmanager.model.Trip;

@Component
public class TripService {
	
	private TripRepository tripRepository;
	
	public TripService(TripRepository tripRepository) {
		// TODO Auto-generated constructor stub
		this.tripRepository = tripRepository;
	}

	public Iterable<Trip> getAllTrips() {
		return  tripRepository.findAll();
	}

	public Trip getTrip(String name) {
		Trip trip = tripRepository.findByName(name);
		if(trip == null) {
			throw new TripNotFoundException();
		}
		return trip;
	}

	public boolean save(Trip trip) {
		// TODO Auto-generated method stub
		return false;
	}

}
