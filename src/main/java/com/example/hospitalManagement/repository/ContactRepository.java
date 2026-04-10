package com.example.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hospitalManagement.model.ContactMessage;

@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Integer> {
}