package com.example.hospitalManagement.controller;

import java.util.Random;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.PatientService;
import jakarta.servlet.http.HttpSession;


@Controller
public class PatientTestcontrol {
	@Autowired
	PatientService ps;
	
	@GetMapping("/")
	public String home() {
		return "home.html";
	}
	
	@GetMapping("/SignUp")
	public String signup() {
		return "signup.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@PostMapping("/registerPatient")
	public String registerPatient(
	        @RequestParam String firstname,
	        @RequestParam String lastname,
	        @RequestParam String number,
	        @RequestParam String email, @RequestParam String password) {
		int pid = 1000 + new Random().nextInt(9000);
		Patient p = new Patient();
		p.setPid(pid);
		p.setFirstname(firstname);
		p.setLastname(lastname);
		p.setNumber(number);
		p.setEmail(email);
		p.setPassword(password);
		ps.insert(p);
	    return "home.html";
	}
	
	@PostMapping("/loginPatient")
	public String loginPatient(@RequestParam String number,
            @RequestParam String password, Model model, HttpSession session) {
		Patient patient = ps.findByNumber(number);
		if (patient != null && patient.getPassword().equals(password)) {
			model.addAttribute("patient", patient);
			session.setAttribute("patient", patient);
		
			 return "patientdashboard.html"; // login success
	    } else {
	        return "login.html"; // login failed
	    }
	}
	
	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {

	    Patient patient = (Patient) session.getAttribute("patient");

	    model.addAttribute("patient", patient);

	    return "myprofile.html";
	}

}
