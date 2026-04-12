package com.example.hospitalManagement.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospitalManagement.model.Appointment;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.AppointmentService;
import com.example.hospitalManagement.service.DoctorService;
import com.example.hospitalManagement.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppointmentController {
	@Autowired
	AppointmentService as;
	@Autowired
	DoctorService ds;
	@Autowired
	PatientService ps;
	
	private void loadAppointment(Model model) {
        model.addAttribute("totalPatients", ps.countPatients());
        model.addAttribute("totalDoctors", ds.countDoctors());
        model.addAttribute("totalAppointment", as.countAppointment());
    }
	
	@GetMapping("/appointments")
	public String loadAppointmnet(HttpSession session, Model model) {
		
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null) {
	        return "redirect:/login";
	    }
		model.addAttribute("patient", patient);
		model.addAttribute("doctors", ds.getAllDoctors());
		return "appointment.html"; 
		
		
	}
	
	@PostMapping("/bookAppointment")
	public String appointmnet(@RequestParam("doctorId") int doctorId,
	                          HttpSession session,
	                          @RequestParam String date,
	                          @RequestParam String time, Model model) {

	    Patient patient = (Patient) session.getAttribute("patient");
		model.addAttribute("patient", patient);
	    if (patient == null) {
	        System.out.println("❌ Session lost");
	        return "redirect:/login";
	    }

	    as.bookAppointment(patient, doctorId, date, time);

	    return "redirect:/patientdashboard";  
	    
	}

}
