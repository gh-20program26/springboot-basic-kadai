package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/confirm")
	public String confirmView() {
		return "confirmView";
	}
	
	@PostMapping("/form")
	public String confirm(
			@Valid ContactForm contactForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
			) {
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute(
					"contactForm",
					contactForm
			);
			
			redirectAttributes.addFlashAttribute(
					BindingResult.MODEL_KEY_PREFIX + "contactForm",
					bindingResult
			);
			
			return "redirect:/contact";
		}
		
		redirectAttributes.addFlashAttribute("contactForm", contactForm);
		
		return "redirect:/confirm";
	}
}
