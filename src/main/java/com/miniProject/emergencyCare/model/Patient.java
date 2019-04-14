package com.miniProject.emergencyCare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
	
	@Id
	@NonNull
	@NotBlank
	private String document;
	
	@NonNull
	@NotBlank
	private String name;
	
	@NonNull
	@NotBlank
	private String lastNames;
	
	private String academicProgram;
	
	private String academicDependence;
	
	private State state;
	
	
	
}
