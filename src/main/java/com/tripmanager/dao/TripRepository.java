package com.tripmanager.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tripmanager.model.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
	public Trip findByName(String name);
}
