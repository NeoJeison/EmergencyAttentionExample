package com.miniProject.emergencyCare.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miniProject.emergencyCare.model.EmergencyAttention;
import com.miniProject.emergencyCare.model.State;
import com.miniProject.emergencyCare.model.Supply;
import com.miniProject.emergencyCare.service.AttentionService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EmergencyCareController {
	
	private AttentionService attentionService;
	
	private State[] state;
	
	@Autowired
	public EmergencyCareController(AttentionService attentionService) {
		this.attentionService = attentionService;
		state = this.attentionService.getStates();
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "logout";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/deliver-medicine")
	public String deliverMedicine(Model model) {
		model.addAttribute("supply", new Supply());
		model.addAttribute("medicines", attentionService.getAllMedicines());
		model.addAttribute("patients", attentionService.getAllPatients());
		return "deliver-medicine";
	}
	
	@PostMapping("/deliver-medicine")
	public String saveDeliveredMedicine(@Valid Supply supply, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("medicines", attentionService.getAllMedicines());
				model.addAttribute("patients", attentionService.getAllPatients());
				return "deliver-medicine";
			}
			try {
				attentionService.deliverMedicine(supply);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		return "index";
	}
	
	@GetMapping("/attend-patient")
	public String attendPatient(Model model) {
		model.addAttribute("attention", new EmergencyAttention());
		model.addAttribute("patients", attentionService.getAllPatients());
		return "attend-patient";
	}
	
	@PostMapping("/attend-patient")
	public String saveAttention(@Valid EmergencyAttention emergencyAttention, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("patients", attentionService.getAllPatients());
				return "attend-patient";
			}
			try {
				attentionService.attendPatient(emergencyAttention);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		return "index";
	}
	
}
