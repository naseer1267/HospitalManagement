package com.example.hospitalManagement.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    int id;
	    LocalDate date;
	    LocalTime time;
	    @ManyToOne
	    @JoinColumn(name = "patient_id")  
	    private Patient patient;
	    @ManyToOne
	    @JoinColumn(name = "doctot_id")  
	    private Doctor doctor;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public LocalTime getTime() {
			return time;
		}
		public void setTime(LocalTime time) {
			this.time = time;
		}
		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient) {
			this.patient = patient;
		}
		public Doctor getDoctor() {
			return doctor;
		}
		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}
		public Appointment() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Appointment(int id, LocalDate date, LocalTime time, Patient patient, Doctor doctor) {
			super();
			this.id = id;
			this.date = date;
			this.time = time;
			this.patient = patient;
			this.doctor = doctor;
		}
	    
	

}
