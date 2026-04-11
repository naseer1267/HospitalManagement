package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalManagement.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
