package com.miniProject.emergencyCare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.miniProject.emergencyCare.model.Medicine;
import com.miniProject.emergencyCare.model.MedicineInventory;

public interface MedicineInventoryRepository extends CrudRepository<MedicineInventory, Long> {
	
	List<MedicineInventory> findByMedicine(Medicine medicine);
	
}
