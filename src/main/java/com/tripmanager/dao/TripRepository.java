package com.tripmanager.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.tripmanager.model.Trip;

public interface TripRepository extends CrudRepository<Trip, Integer> {
	public Trip findByName(String name);
}
