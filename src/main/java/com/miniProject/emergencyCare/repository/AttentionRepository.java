package com.miniProject.emergencyCare.repository;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.EmergencyAttention;

public interface AttentionRepository extends CrudRepository<EmergencyAttention, Long>{
	
	
	
}
