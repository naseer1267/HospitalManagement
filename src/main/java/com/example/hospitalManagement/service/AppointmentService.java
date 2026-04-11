package com.example.hospitalManagement.service;


import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalManagement.model.Appointment;
import com.example.hospitalManagement.model.Doctor;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.PatientRepository;


@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepository ar;
	@Autowired
	PatientRepository pr;
	@Autowired
	DoctorRepository dr;
	
	
	public Appointment bookAppointment(int pid, int id, String date, String time) {
		Patient patient = pr.findByNumber(pid);
		Doctor doctor = dr.findById(id);
		LocalDate localDate = LocalDate.parse(date);
		LocalTime localTime = LocalTime.parse(time);
		Appointment appointment = new Appointment();
	    appointment.setPatient(patient);
	    appointment.setDoctor(doctor);
	    appointment.setDate(localDate);
	    appointment.setTime(localTime);
	    return ar.save(appointment);		
	}

}
