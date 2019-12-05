package com.tripmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.tripmanager.model.Trip;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserTest {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Test
	public void getUser_userIsLoggedIn_LogIn() {
		//act
		ResponseEntity<Trip> response = restTemplate.getForEntity("/dashboard/Mexico", Trip.class);
				
				//assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);				assertThat(response.getBody().getName()).isEqualTo("Mexico");
	}
}
