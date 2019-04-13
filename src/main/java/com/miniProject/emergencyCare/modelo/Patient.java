package com.miniProject.emergencyCare.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Patient {
	
	@Id
	@NonNull
	@NotBlank
	private String document;
	
	@NonNull
	@NotBlank
	private String names;
	
	@NonNull
	@NotBlank
	private String lastNames;
	
	private String academicProgram;
	
	private String academicDependence;
	
	private State state;
	
	
	
}
