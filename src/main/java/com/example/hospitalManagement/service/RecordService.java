package com.example.hospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospitalManagement.model.MedicalRecord;
import com.example.hospitalManagement.repository.RecordRepository;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepo;

    public void saveRecord(MedicalRecord record) {
        recordRepo.save(record);
    }

    public void deleteRecord(int recordId) {
        recordRepo.deleteById(recordId);
    }

    // --- ADD THESE TWO NEW METHODS ---

    // Fetches all records for the logged-in patient
    public List<MedicalRecord> getRecordsByPatientId(int patientId) {
        return recordRepo.findByPatientId(patientId);
    }

    // Fetches a single record to show the full details
    public MedicalRecord getRecordById(int recordId) {
        return recordRepo.findById(recordId).orElse(null);
    }
    
    public long countRecords() {
        return recordRepo.count(); // Automatically counts rows in the Record table
    }
    
}