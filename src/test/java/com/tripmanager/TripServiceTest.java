package com.tripmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tripmanager.dao.TripRepository;
import com.tripmanager.model.Trip;
import com.tripmanager.service.TripService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TripServiceTest {

	@Mock
	private TripRepository tripRepository;

    private TripService tripService;

    @Before
    public void setup() {
    	tripService = new TripService(tripRepository);
    }
	
	@Test
	public void getTrip_returnTripInfo_TripFound() {
	    when(tripRepository.findByName(Mockito.anyString())).thenReturn(new Trip("Mexico"));
		Trip trip = tripService.getTrip("Mexico");
		assertThat(trip.getName()).isEqualTo("Mexico");
	}
	
	
	@Test(expected=TripNotFoundException.class)
	public void getTrip_returnTripInfo_tripNotFound()  throws Exception {
		when(tripRepository.findByName(Mockito.anyString())).thenReturn(null);
		tripService.getTrip("Mexico");
		
	}
	
	@Test
	public void addTrip_tripInformationIsReceived_tripAdded() throws Exception {
		Trip trip = new Trip("Mexico");
		when(tripRepository.save(trip)).thenReturn(trip);
		tripService.addTrip(trip);
		
	}
	
	@Test(expected=Exception.class)
	public void addTrip_tripInformationIsReceived_notAdded() throws Exception {
		Trip trip = new Trip("Mexico");
		when(tripRepository.save(Mockito.any(Trip.class))).thenThrow(Exception.class);
		tripService.addTrip(trip);
		
		
	}
	
	@Test
	public void updateTrip_tripInformationIsReceived_tripAdded() throws Exception {
		Trip trip = new Trip("Mexico");
		when(tripRepository.save(trip)).thenReturn(trip);
		tripService.addTrip(trip);
		
	}
}
