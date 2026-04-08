package com.example.hospitalManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospitalManagement.model.MedicalRecord;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.PatientService;
import com.example.hospitalManagement.service.RecordService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    RecordService recordService;
    
    @Autowired
    PatientService patientService;

    // 1. Shows the Admin Login Page
    @GetMapping("/admin")
    public String adminLogin() {
        return "adminlogin.html";
    }

    // 2. Checks the Admin Credentials & Loads Dashboard
    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String number,
                             @RequestParam String password,
                             Model model,
                             HttpSession session) {

        String adminMobile = "9876543210";
        String adminPass = "admin123";

        if (adminMobile.equals(number) && adminPass.equals(password)) {
            session.setAttribute("admin", "SuperAdmin");
            
            // Fetch all patients and send to the dashboard for the dropdown
            List<Patient> allPatients = patientService.getAllPatients();
            model.addAttribute("patients", allPatients);
            
            return "admindashboard.html"; 
        } else {
            model.addAttribute("error", "Invalid Admin Mobile Number or Password");
            return "adminlogin.html"; 
        }
    }

    // 3. Handles Adding a New Record
    @PostMapping("/addRecord")
    public String addRecord(
            @RequestParam int patientId,
            @RequestParam String recordType,
            @RequestParam LocalDate recordDate,
            @RequestParam String details,
            Model model, HttpSession session) {

        // Security check
        if (session.getAttribute("admin") == null) {
            return "adminlogin.html"; 
        }

        MedicalRecord record = new MedicalRecord();
        record.setPatientId(patientId);
        record.setRecordType(recordType);
        record.setRecordDate(recordDate);
        record.setDetails(details);
        
        recordService.saveRecord(record);

        // Reload the patient list so the dropdown still works after adding
        model.addAttribute("patients", patientService.getAllPatients());
        return "admindashboard.html";
    }

    // 4. Handles Removing an Existing Record
    @PostMapping("/removeRecord")
    public String removeRecord(@RequestParam int recordId, Model model, HttpSession session) {
        
        // Security check
        if (session.getAttribute("admin") == null) {
            return "adminlogin.html"; 
        }

        recordService.deleteRecord(recordId);

        // Reload the patient list so the dropdown still works after removing
        model.addAttribute("patients", patientService.getAllPatients());
        return "admindashboard.html";
    }
}