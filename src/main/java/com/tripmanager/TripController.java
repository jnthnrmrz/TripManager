package com.tripmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tripmanager.model.Trip;
import com.tripmanager.service.TripService;

@RestController
public class TripController {
	
	@Autowired
	TripService tripService;

	@GetMapping("/dashboard") 
	public Iterable<Trip> getAllTrips() {
		return tripService.getAllTrips();
	}
	
	@RequestMapping("/home")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dashboard");
		return mv;
	}
	
	@GetMapping("/dashboard/{name}")
	public Trip getTrip(@PathVariable String name) {
		return tripService.getTrip(name);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void tripNotFoundHandler(TripNotFoundException e) {
		
	}
	
	@PutMapping("/dashboard")
	public void addTrip(Trip trip) {
		tripService.save(trip);
	}
	
}
