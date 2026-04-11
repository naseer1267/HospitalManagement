package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospitalManagement.model.MedicalRecord;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, Integer> {
   
    List<MedicalRecord> findByPatientId(int patientId);
}