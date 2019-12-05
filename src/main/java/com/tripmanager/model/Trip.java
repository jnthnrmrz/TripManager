package com.tripmanager.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trips")
public class Trip {
	@Id
	@GeneratedValue
	@Column(name="trip_id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="start_date")
	private LocalDate startDate; //TODO: possible problems with String and date variables
	@Column(name="end_date")
	private LocalDate endDate;
	@Column(name="location")
	private String location;
	
	public Trip() {
	}
	
	public Trip(String name) {
		this(name, LocalDate.now(), LocalDate.now().plusDays(7), null);
	}
	public Trip(String name, String startDate, String endDate) {
		this(name, LocalDate.parse(startDate), LocalDate.parse(endDate), null);
	}
	public Trip(String name, String startDate, String endDate, String location) {
		this(name, LocalDate.parse(startDate), LocalDate.parse(endDate), location);
	}
	public Trip(String name, LocalDate startDate, LocalDate endDate, String location) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
