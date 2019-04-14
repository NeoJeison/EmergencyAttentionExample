package com.miniProject.emergencyCare.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Supply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long consecutive;
	
	@ManyToOne
	private Medicine medicine;
	
	@NotNull
	@Min(value=1L, message="The quantity must be positive")
	private Integer amount;
	
	@ManyToOne
	private Patient patient;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	@NotNull
	private LocalDate date;
	
	@DateTimeFormat(pattern="HH:mm")
	@NotNull
	private LocalTime time;
	
	private String observation;
	
	@NonNull
	@NotBlank
	private String pathology;
	
}
