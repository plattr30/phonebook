package com.rdplatt.phonebook.controller;

import com.rdplatt.phonebook.model.Contact;
import com.rdplatt.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping("/contacts")
    public Contact create(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(value = "id") Long contactId) {
        Contact contact = contactRepository.findOne(contactId);
        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable(value = "id") Long contactId,
                           @Valid @RequestBody Contact contactDetails) {

        Contact contact = contactRepository.findOne(contactId);


        contact.setFirstName(contactDetails.getFirstName());
        contact.setLastName(contactDetails.getLastName());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());

        return contactRepository.save(contact);
    }
}
