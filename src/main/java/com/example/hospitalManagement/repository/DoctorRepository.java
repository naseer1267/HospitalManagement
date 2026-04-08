package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalManagement.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
