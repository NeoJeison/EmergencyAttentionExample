package com.miniProject.emergencyCare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miniProject.emergencyCare.model.EmergencyAttention;
import com.miniProject.emergencyCare.model.Medicine;
import com.miniProject.emergencyCare.model.MedicineInventory;
import com.miniProject.emergencyCare.model.MyUserPrincipal;
import com.miniProject.emergencyCare.model.Patient;
import com.miniProject.emergencyCare.model.State;
import com.miniProject.emergencyCare.model.Supply;
import com.miniProject.emergencyCare.model.User;
import com.miniProject.emergencyCare.repository.AttentionRepository;
import com.miniProject.emergencyCare.repository.MedicineInventoryRepository;
import com.miniProject.emergencyCare.repository.MedicineRepository;
import com.miniProject.emergencyCare.repository.PatientRepository;
import com.miniProject.emergencyCare.repository.SupplyRepository;
import com.miniProject.emergencyCare.repository.UserRepository;

@Service
public class AttentionServiceImp implements AttentionService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private AttentionRepository attentionRepository;

	@Autowired
	private SupplyRepository supplyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MedicineInventoryRepository medicineInventoryRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void createUsers()
	{
		
		// Adding default users
		
        User user = new User();
        user.setUsername("NeoJt");
        user.setName("Jeison");
        user.setLastNames("Mejia Trujillo");
        user.setPassword(passwordEncoder.encode("123"));
        user.setState(State.Active);
        userRepository.save(user);
        
        User user2 = new User();
        user2.setUsername("JAtom");
        user2.setName("Juan");
        user2.setLastNames("Perez Pum");
        user2.setPassword(passwordEncoder.encode("123"));
        user2.setState(State.Active);
        userRepository.save(user2);
        
        // Adding default patients
        
        Patient patient = new Patient("123456", "Jeison", "Mejia Trujilo");
        patient.setAcademicDependence("Ninguna");
        patient.setAcademicProgram("Sistemas");
        patient.setState(State.Active);
        Patient patient2 = new Patient("654321", "Juan", "Perez Pum");
        patient2.setAcademicDependence("Ninguna");
        patient2.setAcademicProgram("Telemática");
        patient2.setState(State.Active);
        Patient patient3 = new Patient("321654", "Ana", "Rodriguez Pam");
        patient3.setAcademicDependence("Ninguna");
        patient3.setAcademicProgram("Industrial");
        patient3.setState(State.Inactive);
        patientRepository.save(patient);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        
        // Adding default medicines (and medicine inventories)
        //Medicine inventories don't have medicine (value=null) because of how crud repository works. I had to initialize
        //one of the objects first and added to the repository (medicine or medicine inventory)
        //the other one had to stay with the dependency null.
        
        Medicine medicine = new Medicine("Acetaminofen", "Acetaminofen", "JGB", "Oral");
        
        MedicineInventory medicineInventory1 = new MedicineInventory(12, "Vitrina");
        medicineInventory1.setExpirationDate(LocalDate.of(2020, 2, 15));
        medicineInventoryRepository.save(medicineInventory1);
        MedicineInventory medicineInventory2 = new MedicineInventory(50, "Bodega trasera");
        medicineInventory2.setExpirationDate(LocalDate.of(2019, 11, 15));
        medicineInventoryRepository.save(medicineInventory2);
        MedicineInventory medicineInventory3 = new MedicineInventory(50, "Bodega secundaria");
        medicineInventory3.setExpirationDate(LocalDate.of(2021, 5, 20));
        medicineInventoryRepository.save(medicineInventory3);
        
        List<MedicineInventory> medInv = new ArrayList<MedicineInventory>();
        medInv.add(medicineInventory1);
        medInv.add(medicineInventory2);
        medInv.add(medicineInventory3);
        medicine.setMedicineInventory(medInv);
        medicineRepository.save(medicine);
        
        medicineInventoryRepository.findById(medicineInventory1.getConsecutive()).get().setMedicine(medicine);
        medicineInventoryRepository.findById(medicineInventory2.getConsecutive()).get().setMedicine(medicine);
        medicineInventoryRepository.findById(medicineInventory3.getConsecutive()).get().setMedicine(medicine);
        
        Medicine medicine2 = new Medicine("Diclofenaco", "Diclofenaco", "Genfar", "Intramuscular");
        MedicineInventory medicine2Inventory1 = new MedicineInventory(5, "Vitrina");
        medicine2Inventory1.setExpirationDate(LocalDate.of(2020, 2, 15));
        medicineInventoryRepository.save(medicine2Inventory1);
        MedicineInventory medicine2Inventory2 = new MedicineInventory(20, "Bodega trasera");
        medicine2Inventory2.setExpirationDate(LocalDate.of(2019, 10, 15));
        medicineInventoryRepository.save(medicine2Inventory2);
        MedicineInventory medicine2Inventory3 = new MedicineInventory(23, "Bodega secundaria");
        medicine2Inventory3.setExpirationDate(LocalDate.of(2021, 5, 20));
        medicineInventoryRepository.save(medicine2Inventory3);
        medInv = new ArrayList<MedicineInventory>();
        medInv.add(medicine2Inventory1);
        medInv.add(medicine2Inventory2);
        medInv.add(medicine2Inventory3);
        medicine2.setMedicineInventory(medInv);
        medicineRepository.save(medicine2);
        
        medicineInventoryRepository.findById(medicine2Inventory1.getConsecutive()).get().setMedicine(medicine2);
        medicineInventoryRepository.findById(medicine2Inventory2.getConsecutive()).get().setMedicine(medicine2);
        medicineInventoryRepository.findById(medicine2Inventory3.getConsecutive()).get().setMedicine(medicine2);
        
        Medicine medicine3 = new Medicine("Naproxeno", "Naproxeno", "Genfar", "Oral");
        MedicineInventory medicine3Inventory1 = new MedicineInventory(5, "Vitrina");
        medicine3Inventory1.setExpirationDate(LocalDate.of(2020, 2, 15));
        medicineInventoryRepository.save(medicine3Inventory1);
        MedicineInventory medicine3Inventory2 = new MedicineInventory(23, "Bodega secundaria");
        medicine3Inventory2.setExpirationDate(LocalDate.of(2020, 5, 20));
        medicineInventoryRepository.save(medicine3Inventory2);
        medInv = new ArrayList<MedicineInventory>();
        medInv.add(medicine3Inventory1);
        medInv.add(medicine3Inventory2);
        medicine3.setMedicineInventory(medInv);
        medicineRepository.save(medicine3);
        
        medicineInventoryRepository.findById(medicine3Inventory1.getConsecutive()).get().setMedicine(medicine3);
        medicineInventoryRepository.findById(medicine3Inventory2.getConsecutive()).get().setMedicine(medicine3);
        
        
	}
	
	@Override
	public void deliverMedicine(Supply supply) throws Exception {
		
		verifyUser(supply.getPatient());
		verifyMedicineExists(supply.getMedicine());
		verifyAmountOfMedicine(supply.getMedicine(), supply.getAmount());
		updateAvailableMedicine(supply);
		supplyRepository.save(supply);
//		System.out.println(supplyRepository.findAll());

	}

	private void verifyMedicineExists(Medicine medicine) throws Exception {
		if (medicineRepository.findById(medicine.getConsecutive()) == null) {
			throw new Exception("El medicamento " + medicine.getGenericName() + " no está registrado en el sistema.");
		}
	}

	private void verifyUser(Patient patient) throws Exception {
		if (patientRepository.findById(patient.getDocument()) == null) {
			throw new Exception("El paciente " + patient.getName() + " identificado con el documento " 
					+ patient.getDocument() + " no se encuentra registrado en el sistema.");
		} else if (patient.getState().equals(State.Inactive)) {
			throw new Exception("El paciente " + patient.getName() + " identificado con el documento " 
					+ patient.getDocument() + " se encuentra inactivo.");
		}
	}

	private void verifyAmountOfMedicine(Medicine medicine, int Needed) throws Exception {
		int amountAux = Needed;
		for (int i = 0; i < medicine.getMedicineInventory().size() && amountAux != 0; i++) {
			if (medicine.getMedicineInventory().get(i).getQuantityAvailable() > amountAux) {
				return;
			} else {
				amountAux -= medicine.getMedicineInventory().get(i).getQuantityAvailable();
			}
		}
		if (amountAux != 0) {
			throw new Exception("La cantidad disponible de " + medicine.getGenericName() + " es menor a " + Needed + ".");
		}
	}

	@Override
	public void updateAvailableMedicine(Supply supply) {
		int amountAux = supply.getAmount();
		for (int i = 0; i < supply.getMedicine().getMedicineInventory().size() && amountAux != 0; i++) {
			if (supply.getMedicine().getMedicineInventory().get(i).getQuantityAvailable() > amountAux) {
				supply.getMedicine().getMedicineInventory().get(i).setQuantityAvailable(
						supply.getMedicine().getMedicineInventory().get(i).getQuantityAvailable()
								- amountAux);
				return;
			} else {
				amountAux -= supply.getMedicine().getMedicineInventory().get(i).getQuantityAvailable();
				supply.getMedicine().getMedicineInventory().remove(i);
				i--;
			}
		}
	}

	@Override
	public void attendPatient(EmergencyAttention emergencyAttention) throws Exception {
//		for (int i = 0; i < emergencyAttention.getMedicinesSupplied().size(); i++) {
//			deliverMedicine(emergencyAttention.getMedicinesSupplied().get(i));
//		}
		attentionRepository.save(emergencyAttention);
		System.out.println(attentionRepository.findAll());
		System.out.println(supplyRepository.findAll());
	}
	
	public State[] getStates() {
		return State.values();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException(username);
        }
        return new MyUserPrincipal(user.get());
	}

	@Override
	public Iterable<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public Iterable<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	@Override
	public EmergencyAttention getAttentionById(Long id) {
		return attentionRepository.findById(id).get();
	}
	
	@Override
	public Supply getSupplyById(Long id) {
		return supplyRepository.findById(id).get();
	}
}
