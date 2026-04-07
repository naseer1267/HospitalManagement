package com.example.hospitalManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {
	@Id
	int pid;
	String firstname;
	String lastname;
	String	 number;
	String email;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number2) {
		this.number = number2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Patient(int pid, String firstname, String lastname, String number, String email, String password) {
		super();
		this.pid = pid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.number = number;
		this.email = email;
		this.password = password;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
