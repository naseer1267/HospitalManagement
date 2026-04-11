package com.example.hospitalManagement.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospitalManagement.model.Appointment;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.AppointmentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppointmentController {
	@Autowired
	AppointmentService as;
	
	@GetMapping("/appointment")
	public String loadAppointmnet(HttpSession session) {
		return "appointmnet.html";
		
		
	}
	
	@PostMapping("/bookAppoitment")
	public String appointmnet(@RequestParam int id, HttpSession session, @RequestParam String date, @RequestParam String time) {
		Patient patient = (Patient) session.getAttribute("patient");
		as.bookAppointment(patient.getPid(), id, date, time);
		return "appointment.html";
	}
	

}
