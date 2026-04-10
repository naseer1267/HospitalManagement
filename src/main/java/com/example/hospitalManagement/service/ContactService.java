package com.example.hospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospitalManagement.model.ContactMessage;
import com.example.hospitalManagement.repository.ContactRepository;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepo;

    public void saveMessage(ContactMessage msg) {
        contactRepo.save(msg);
    }
}