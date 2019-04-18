package com.miniProject.emergencyCare.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Long>{
	
	public List<Supply> findByDate(LocalDate date);
	
}
