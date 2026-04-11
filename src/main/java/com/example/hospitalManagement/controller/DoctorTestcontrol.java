package com.example.hospitalManagement.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hospitalManagement.model.Doctor;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorTestcontrol {
    
    @Autowired
    DoctorService ds;

    @PostMapping("/addDoctor")
    public String addDoctor(
            @RequestParam String name,
            @RequestParam String specialization,
            @RequestParam String qualification,
            @RequestParam int experience,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam double fee,
            RedirectAttributes redirectAttributes) {
        
        int id = 1000 + new Random().nextInt(9000); 
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setQualification(qualification);
        doctor.setExperience(experience);
        doctor.setPhone(phone);
        doctor.setEmail(email);
        doctor.setFee(fee);
        
        ds.insert(doctor);
        
       
        redirectAttributes.addFlashAttribute("message", "Doctor Saved Successfully!");
        redirectAttributes.addFlashAttribute("activeTab", "doctors");
        
        
        return "redirect:/adminDashboard"; 
    }

    @PostMapping("/removeDoctor")
    public String removeDoctor(@RequestParam int id, RedirectAttributes redirectAttributes) {
        ds.deleteById(id);
        
       
        redirectAttributes.addFlashAttribute("message", "Doctor Removed!");
        redirectAttributes.addFlashAttribute("activeTab", "doctors");
        
        
        return "redirect:/adminDashboard";
    }

    @GetMapping("/doctors")
    public String getDoctors(Model model) {
        List<Doctor> doctors = ds.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctorDisplay.html";
    }

    @GetMapping("/displayDashboard")
    public String dashboard(Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        if (patient == null) {
        }
        model.addAttribute("patient", patient);
        return "patientdashboard.html";
    }
}