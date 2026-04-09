package com.example.hospitalManagement.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // 🔹 Added missing import

import com.example.hospitalManagement.model.MedicalRecord;
import com.example.hospitalManagement.service.DoctorService;
import com.example.hospitalManagement.service.PatientService;
import com.example.hospitalManagement.service.RecordService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    RecordService recordService;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    DoctorService doctorService;
    
    // Helper method to load all stats
    private void loadDashboardStats(Model model) {
        model.addAttribute("totalPatients", patientService.countPatients());
        model.addAttribute("totalDoctors", doctorService.countDoctors());
        model.addAttribute("totalRecords", recordService.countRecords());
    }

    @GetMapping("/admin")
    public String adminLogin() {
        return "adminlogin.html";
    }
    
    @GetMapping("/adminDashboard")
    public String loadAdminDashboard(Model model, HttpSession session) {
        // Security check
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin";
        }
        
        // 🔹 Much cleaner! Fetch patients and use the helper method for the stats
        model.addAttribute("patients", patientService.getAllPatients());
        loadDashboardStats(model);
        
        return "admindashboard.html";
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String number, 
                             @RequestParam String password, 
                             RedirectAttributes redirectAttributes, 
                             HttpSession session) {
        String adminMobile = "9876543210";
        String adminPass = "admin123";
        
        if (adminMobile.equals(number) && adminPass.equals(password)) {
            session.setAttribute("admin", "SuperAdmin");
            redirectAttributes.addFlashAttribute("activeTab", "dashboard");
            return "redirect:/adminDashboard"; 
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid Admin Mobile Number or Password");
            return "redirect:/admin";
        }
    }

    @PostMapping("/addRecord")
    public String addRecord(
            @RequestParam int patientId, @RequestParam String recordType,
            @RequestParam LocalDate recordDate, @RequestParam String details,
            RedirectAttributes redirectAttributes, HttpSession session) {
        
        if (session.getAttribute("admin") == null) return "redirect:/admin";
        
        MedicalRecord record = new MedicalRecord();
        record.setPatientId(patientId);
        record.setRecordType(recordType);
        record.setRecordDate(recordDate);
        record.setDetails(details);

        recordService.saveRecord(record);

        redirectAttributes.addFlashAttribute("successMessage", "✅ Record Added Successfully!");
        redirectAttributes.addFlashAttribute("activeTab", "records");

        return "redirect:/adminDashboard";
    }

    @PostMapping("/removeRecord")
    public String removeRecord(@RequestParam int recordId, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin";
        
        recordService.deleteRecord(recordId);

        redirectAttributes.addFlashAttribute("successMessage", "🗑️ Record Removed Successfully!");
        redirectAttributes.addFlashAttribute("activeTab", "records");

        return "redirect:/adminDashboard";
    }
}