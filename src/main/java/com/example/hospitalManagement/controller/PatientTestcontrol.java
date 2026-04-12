package com.example.hospitalManagement.controller;

import java.util.Random;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hospitalManagement.model.Patient;
import com.example.hospitalManagement.service.PatientService;
import jakarta.servlet.http.HttpSession;
import com.example.hospitalManagement.service.RecordService;
import com.example.hospitalManagement.model.MedicalRecord;
import java.util.List;
import com.example.hospitalManagement.model.ContactMessage;
import com.example.hospitalManagement.service.ContactService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PatientTestcontrol {
    
    @Autowired
    PatientService ps;
    
    @Autowired
    RecordService recordService;
    
    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/SignUp")
    public String signup() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/registerPatient")
    public String registerPatient(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String number,
            @RequestParam String email, 
            @RequestParam String password) {
            
        int pid = 1000 + new Random().nextInt(9000);
        Patient p = new Patient();
        p.setPid(pid);
        p.setFirstname(firstname);
        p.setLastname(lastname);
        p.setNumber(number);
        p.setEmail(email);
        p.setPassword(password);
        ps.insert(p);
        
        return "home.html";
    }

    @PostMapping("/loginPatient")
    public String loginPatient(@RequestParam long number,
                               @RequestParam String password, Model model, HttpSession session) {
        Patient patient = ps.findByNumber(number);
        if (patient != null && patient.getPassword().equals(password)) {
            model.addAttribute("patient", patient);
            session.setAttribute("patient", patient);
            return "redirect:/patientdashboard";
        } else {
            return "login.html"; 
        } 
    }
    @GetMapping("/patientdashboard")
    public String dashboard(HttpSession session, Model model) {
        Patient patient = (Patient) session.getAttribute("patient");
        if (patient == null) {
            return "redirect:/login";  // 🔥 this is what is happening now
        }
        model.addAttribute("patient", patient);
        return "patientdashboard";
    } 


    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        model.addAttribute("patient", patient);
        return "myprofile.html";
    }
 
    @GetMapping("/reports")
    public String viewReports(Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        if (patient == null) {
            return "redirect:/login";
        }
        
       
        List<MedicalRecord> records = recordService.getRecordsByPatientId(patient.getPid());
        model.addAttribute("records", records);
        
        return "reports.html";
    }

  
    @GetMapping("/reportDetails")
    public String viewReportDetails(@RequestParam int recordId, Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        if (patient == null) {
            return "redirect:/login"; 
        }

      
        MedicalRecord record = recordService.getRecordById(recordId);
        model.addAttribute("record", record);
        
        return "reportDetails.html";
    }
    



    @PostMapping("/submitContact")
    public String submitContact(@RequestParam String name, 
                                @RequestParam String email, 
                                @RequestParam String message, 
                                RedirectAttributes redirectAttributes) {
        
        ContactMessage msg = new ContactMessage(name, email, message);
        contactService.saveMessage(msg);
        
        // Add a success message to show on the home page
        redirectAttributes.addFlashAttribute("success", "Thank you! Your message has been sent.");
        
        return "redirect:/"; // Redirect back to home

    }
}