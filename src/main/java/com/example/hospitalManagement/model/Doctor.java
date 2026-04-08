package com.example.hospitalManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {
	@Id
	 int id;   
     String name;
     String specialization;
     String qualification;
     int experience;
     String phone;
     String email;
     double fee;
     public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	 public Doctor(int id, String name, String specialization, String qualification, int experience, String phone,
			String email, double fee) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.qualification = qualification;
		this.experience = experience;
		this.phone = phone;
		this.email = email;
		this.fee = fee;
	}
	 public int getId() {
		return id;
	}
	 public void setId(int id) {
		 this.id = id;
	 }
	 public String getName() {
		 return name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 public String getSpecialization() {
		 return specialization;
	 }
	 public void setSpecialization(String specialization) {
		 this.specialization = specialization;
	 }
	 public String getQualification() {
		 return qualification;
	 }
	 public void setQualification(String qualification) {
		 this.qualification = qualification;
	 }
	 public int getExperience() {
		 return experience;
	 }
	 public void setExperience(int experience) {
		 this.experience = experience;
	 }
	 public String getPhone() {
		 return phone;
	 }
	 public void setPhone(String phone) {
		 this.phone = phone;
	 }
	 public String getEmail() {
		 return email;
	 }
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 public double getFee() {
		 return fee;
	 }
	 public void setFee(double fee) {
		 this.fee = fee;
	 }
	 

}
