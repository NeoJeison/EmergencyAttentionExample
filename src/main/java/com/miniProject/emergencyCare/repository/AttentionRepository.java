package com.miniProject.emergencyCare.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.EmergencyAttention;

public interface AttentionRepository extends CrudRepository<EmergencyAttention, Long>{
	
	public List<EmergencyAttention> findByDate(LocalDate date);
	
}
