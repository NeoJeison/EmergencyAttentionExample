package com.miniProject.emergencyCare.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
}
