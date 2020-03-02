package com.tripmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tripmanager.dao.TripRepository;
import com.tripmanager.dao.UserRepository;
import com.tripmanager.model.Trip;
import com.tripmanager.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class UserRepositoryTest {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void findByUsername_userIsFound_getUser()  {
		User savedUser = entityManager.persistFlushFind(new User("jonathan", "password"));
		User user = userRepository.findByUsername("jonathan");
		
		assertThat(user.getUsername()).isEqualTo(savedUser.getUsername());
	}
	
	@Test
	public void save_userAdded_saveUser() throws Exception  {
		User user = new User("Jonathan", "Password");
		user = entityManager.persistFlushFind(user);
		assertThat(userRepository.findById(user.getId()).get()).isEqualTo(user);	
	}
	
	/**
	 * This is because database only accepts unique usernames
	 * @throws Exception
	*/
	@Test(expected=Exception.class) 
	public void save_tripAdded_saveTripFailed() throws Exception  {
		User check = new User("default", "password"); 
		check = entityManager.persistFlushFind(check);
		assertThat(userRepository.findById(check.getId()).get()).isEqualTo(check);
	}

	
	@Test
	public void delete_UserIsDeleted_tripDeletionSuccessful() {
		User check = new User("Jane Doe", "password");
		check = entityManager.persistFlushFind(check);
		assertThat(userRepository.delete(userRepository.findByUsername("Spain")).isEqualTo(null));
	}
}
