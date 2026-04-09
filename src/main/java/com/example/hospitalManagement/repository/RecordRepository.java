package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospitalManagement.model.MedicalRecord;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, Integer> {
    // Add this line to fetch records belonging to a specific patient
    List<MedicalRecord> findByPatientId(int patientId);
}