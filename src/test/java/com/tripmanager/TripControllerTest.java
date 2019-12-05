package com.tripmanager;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.tripmanager.model.Trip;
import com.tripmanager.service.TripService;



@RunWith(SpringRunner.class)
@WebMvcTest(TripController.class)
public class TripControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TripService tripService;
	
	@Test
	public void getAllTrips_userTripsIsDisplayed_returnAllTrips() throws Exception {
		List<Trip> expectedResult = new ArrayList<Trip>();
		expectedResult.add(new Trip("Barcelona"));
		expectedResult.add(new Trip("Mexico"));
		
		when(tripService.getAllTrips()).thenReturn(expectedResult);
		
	//"$[1].name" works as follows. It is the key at [1] called name
		mockMvc.perform(MockMvcRequestBuilders.get("/dashboard"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("[0].name", is("Barcelona")));
		
	}
	
	@Test
	public void getTrip_userTripIsClicked_tripIsFound() throws Exception {
		Trip trip = new Trip("California");
		when(tripService.getTrip("California")).thenReturn(trip);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/dashboard/California"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("California")));
	}
	
	@Test
	public void getTrip_userTripIsClicked_tripIsNotFound() throws Exception {
		when(tripService.getTrip(Mockito.anyString())).thenThrow(new TripNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/dashboard/California"))
				.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void addTrip_userPutsTripInfo_tripIsAdded() throws Exception {
		when(tripService.save(new Trip("Mexico"))).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/dashboard"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("Mexico")));
	}
}
