package com.example.hospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospitalManagement.model.MedicalRecord;
import com.example.hospitalManagement.repository.RecordRepository;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepo;

    // Method to save a new record
    public void saveRecord(MedicalRecord record) {
        recordRepo.save(record);
    }

    // Method to delete an existing record by its ID
    public void deleteRecord(int recordId) {
        recordRepo.deleteById(recordId);
    }
}