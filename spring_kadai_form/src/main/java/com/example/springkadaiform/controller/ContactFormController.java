package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

import jakarta.validation.Valid;

@Controller
public class ContactFormController {
	
	@GetMapping("/contact")
	public String showForm(Model model) {
		if (!model.containsAttribute("contactForm")) {
			model.addAttribute("contactForm", new ContactForm());
		}
		return "contactFormView";
	}
	
	@PostMapping("/form")
	public String confirm(
			@Valid ContactForm contactForm,
			BindingResult bindingResult,
			Model model
			) {
		if (bindingResult.hasErrors()) {
			return "contactFormView";
		}
		model.addAttribute("contactForm", contactForm);
		return "confirmView";
	}
}
