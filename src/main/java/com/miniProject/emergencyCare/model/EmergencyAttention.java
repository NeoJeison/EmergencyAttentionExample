package com.miniProject.emergencyCare.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class EmergencyAttention {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long consecutive;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	@NotNull
	private LocalDate date;
	
	@DateTimeFormat(pattern="HH:mm")
	@NotNull
	private LocalTime time;
	
	@ManyToOne
	private Patient patient;
	
	@NonNull
	@NotBlank
	private String generalDescription;
	
	@NonNull
	@NotBlank
	private String procedurePerformed;
	
	@NonNull
	private Boolean forwarded;
	
	private String forwardedPlace;
	
	@NonNull
	@NotBlank
	private String observations;
	
	@OneToMany
	private List<Supply> medicinesSupplied;
	
}
