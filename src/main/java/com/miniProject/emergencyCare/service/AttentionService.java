package com.miniProject.emergencyCare.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miniProject.emergencyCare.model.EmergencyAttention;
import com.miniProject.emergencyCare.model.Medicine;
import com.miniProject.emergencyCare.model.Patient;
import com.miniProject.emergencyCare.model.State;
import com.miniProject.emergencyCare.model.Supply;

public interface AttentionService extends UserDetailsService{

	public void deliverMedicine(Supply supply) throws Exception;

	public void updateAvailableMedicine(Supply supply);

	public void attendPatient(EmergencyAttention emergencyAttention) throws Exception;
	
	public State[] getStates();
	
	public Iterable<Medicine> getAllMedicines();
	
	public Iterable<Patient> getAllPatients();

}
