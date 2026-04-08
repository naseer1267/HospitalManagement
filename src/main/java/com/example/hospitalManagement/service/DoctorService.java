package com.example.hospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalManagement.model.Doctor;
import com.example.hospitalManagement.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	DoctorRepository dr;
	
	public void insert(Doctor d) {
		dr.save(d);
	}
	public void deleteById(int id) {

        if (dr.existsById(id)) {
            dr.deleteById(id);
        } else {
            throw new RuntimeException("Doctor not found with ID: " + id);
        }
    }

}
