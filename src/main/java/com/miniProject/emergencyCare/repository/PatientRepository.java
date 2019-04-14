package com.miniProject.emergencyCare.repository;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, String>{
	
	
	
}
