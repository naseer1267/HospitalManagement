package com.example.hospitalManagement.service;

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

}
