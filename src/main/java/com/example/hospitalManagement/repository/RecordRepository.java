package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospitalManagement.model.MedicalRecord;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, Integer> {
    // Spring Boot automatically provides save(), deleteById(), and findAll() methods!
}