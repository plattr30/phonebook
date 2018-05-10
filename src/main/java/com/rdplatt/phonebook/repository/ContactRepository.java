package com.rdplatt.phonebook.repository;

import com.rdplatt.phonebook.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
