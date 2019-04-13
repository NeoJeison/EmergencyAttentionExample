package com.miniProject.emergencyCare.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//Login
	@NotBlank
	@NonNull
	private String username;
	
	@NotBlank
	@NonNull
	private String password;
	
	@NonNull
	@NotBlank
	private String name;
	
	@NonNull
	@NotBlank
	private String lastNames;
	
	private State state;
	
}
