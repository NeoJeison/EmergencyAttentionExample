package com.miniProject.emergencyCare.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String saveDeliveredMedicine(@Valid @ModelAttribute(value = "supply") Supply supply,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action,
			@RequestParam(value = "idAttention", defaultValue = "") String idAttention) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("idAttention", idAttention);
				model.addAttribute("medicines", attentionService.getAllMedicines());
				model.addAttribute("patients", attentionService.getAllPatients());
				return "deliver-medicine";
			}
			try {
				if (!idAttention.equals("")) {
					attentionService.deliverMedicine(supply);
					EmergencyAttention eA = attentionService.getAttentionById(Long.parseLong(idAttention));
					eA.getMedicinesSupplied().add(supply);
					attentionService.attendPatient(eA);
					model.addAttribute("attention", attentionService.getAttentionById(Long.parseLong(idAttention)));
					model.addAttribute("patients", attentionService.getAllPatients());
					return "attend-patient";
				}
				attentionService.deliverMedicine(supply);
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
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
	public String saveAttention(@Valid @ModelAttribute(value = "attention") EmergencyAttention emergencyAttention,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("attenion", emergencyAttention);
				model.addAttribute("patients", attentionService.getAllPatients());
				return "attend-patient";
			}
			try {
				if (action.equals("Add supply")) {
					if (emergencyAttention.getConsecutive() != null) {
						emergencyAttention.setMedicinesSupplied(attentionService.getAttentionById(emergencyAttention.getConsecutive()).getMedicinesSupplied());
					}
					attentionService.attendPatient(emergencyAttention);
					model.addAttribute("idAttention", emergencyAttention.getConsecutive());
					return deliverMedicine(model);
				}
				emergencyAttention.setMedicinesSupplied(attentionService.getAttentionById(emergencyAttention.getConsecutive()).getMedicinesSupplied());
				attentionService.attendPatient(emergencyAttention);
				attentionService.attendPatient(emergencyAttention);
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return "index";
	}
	
	@GetMapping("/query-options")
	public String queryOptions() {
		return "query-options";
	}
	
	@GetMapping("/make-query")
	public String makeQuery(@RequestParam(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date, Model model, @RequestParam(value = "action", required = true) String action) {
		if(action.equals("Query attentions")) {
			model.addAttribute("attentions", attentionService.findAttentionsByDate(date));
			return "attentions-query-result";
		}
		model.addAttribute("supplies", attentionService.findSuppliesByDate(date));
		return "supplies-query-result";
	}

}
