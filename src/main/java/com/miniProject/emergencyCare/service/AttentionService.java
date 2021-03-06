package com.miniProject.emergencyCare.service;

import java.time.LocalDate;
import java.util.List;

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
	
	public EmergencyAttention getAttentionById(Long id);
	
	public Supply getSupplyById(Long id);
	
	public List<EmergencyAttention> findAttentionsByDate(LocalDate date);
	
	public List<Supply> findSuppliesByDate(LocalDate date);


}
