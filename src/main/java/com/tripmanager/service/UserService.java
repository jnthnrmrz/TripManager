package com.tripmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmanager.dao.UserRepository;
import com.tripmanager.model.User;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepository;	
	
	/**
	 * Adds a user 
	 * @param user The user information
	 */
	public void addUser(User user) {
		userRepository.save(user);
	}
}
