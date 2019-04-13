package com.miniProject.emergencyCare.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Supply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long consecutive;
	
	@ManyToOne
	private Medicine medicine;
	
	@Min(value=0L, message="The quantity must be positive")
	private Integer cantidad;
	
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
	private String pathology;
	
}
