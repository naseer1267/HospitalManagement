package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalManagement.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByNumber(String number);

}
