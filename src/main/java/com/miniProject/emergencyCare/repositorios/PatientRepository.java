package com.miniProject.emergencyCare.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.modelo.Patient;

public interface PatientRepository extends CrudRepository<Patient, String>{
	
	
	
}
