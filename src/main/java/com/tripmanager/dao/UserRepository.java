package com.tripmanager.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tripmanager.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByUsername(String username);
}
