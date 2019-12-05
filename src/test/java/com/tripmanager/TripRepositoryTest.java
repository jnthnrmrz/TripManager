package com.tripmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tripmanager.dao.TripRepository;
import com.tripmanager.model.Trip;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TripRepositoryTest {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void findByName_tripIsChosen_getTrip()  {
		Trip savedTrip = entityManager.persistFlushFind(new Trip("Canada"));
		Trip trip = tripRepository.findByName("Canada");
		
		assertThat(trip.getName()).isEqualTo(savedTrip.getName());
	}
}
