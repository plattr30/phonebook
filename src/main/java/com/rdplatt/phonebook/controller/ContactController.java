package com.rdplatt.phonebook.controller;

import com.rdplatt.phonebook.model.Contact;
import com.rdplatt.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contact")
    public Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping("/contact")
    public Contact create(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }
}
