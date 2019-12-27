package com.tripmanager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tripmanager.model.Trip;
import com.tripmanager.service.TripService;

@Controller
public class TripController {
	
	@Autowired
	TripService tripService;

	@RequestMapping("/")
	public String redirectDashboard() {
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard") 
	public String getAllTrips(Model model) {
		model.addAttribute("trips", tripService.getAllTrips());
		model.addAttribute("trip", new Trip());
		model.addAttribute("tripEdit", new Trip());
		//return tripService.getAllTrips();
		return "dashboard.html";
	}
	
	@PostMapping("/dashboard")
	public String addTrip(@Valid Trip trip, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		try { 
			tripService.addTrip(trip);
		} catch(Exception e ) {
			System.out.println("Exception was found when saving");
		}
		return "redirect:/dashboard";
	}
	
	@PutMapping("/dashboard")
	public String updateTrip(Trip trip) {
		
		return "redirect:/dashboard";
	}
	
	@DeleteMapping("/dashboard")
	public String deleteTrip(@RequestParam("id") int id, Trip trip) {
		tripService.deleteTrip(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard/trip{id}")
	public String getTrip(@PathVariable int id, Model model) {
		Trip trip = tripService.getTrip(id);
		model.addAttribute("trip", trip);
		return "trip.html";
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void tripNotFoundHandler(TripNotFoundException e) {
		
	}
	
}
