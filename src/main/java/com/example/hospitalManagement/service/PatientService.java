package com.example.hospitalManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.repository.PatientRespository;

@Service
public class PatientService {
	@Autowired
	PatientRespository pr;
	
	public void insert(Patient p) {
		pr.save(p);
		
	}
	
	public Patient findByNumber(String number) {
	    return pr.findByNumber(number);
	}
	
	public List<Patient> getAllPatients() {
	    return pr.findAll(); // Fetches all patients from the repository
	}
	
	public long countPatients() {
	    return pr.count(); // Automatically counts rows in the Patient table
	}
 
}
