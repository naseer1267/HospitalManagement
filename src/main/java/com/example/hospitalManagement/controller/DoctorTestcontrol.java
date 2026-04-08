package com.example.hospitalManagement.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hospitalManagement.model.Doctor;
import com.example.hospitalManagement.service.DoctorService;

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

	    int id = 1000 + new Random().nextInt(9000); // optional

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

	    // 🔥 Correct usage
	    redirectAttributes.addFlashAttribute("message", "Doctor Saved Successfully!");

	    return "redirect:/admindashboard"; // 🔥 VERY IMPORTANT
	}
    
    @PostMapping("/removeDoctor")
    public String removeDoctor(@RequestParam int id,
                               RedirectAttributes redirectAttributes) {

        ds.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Doctor Removed!");

        return "redirect:/admin";
    }
	

}
