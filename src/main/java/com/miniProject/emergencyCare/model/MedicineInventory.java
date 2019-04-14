package com.miniProject.emergencyCare.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class MedicineInventory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long consecutive;
	
	@ManyToOne
	private Medicine medicine;
	
	@NonNull
	@Min(value=0L, message="The quantity must be positive")
	private Integer quantityAvailable;
	
	@NonNull
	@NotBlank
	private String location;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@FutureOrPresent
	private LocalDate expirationDate;
	
}
