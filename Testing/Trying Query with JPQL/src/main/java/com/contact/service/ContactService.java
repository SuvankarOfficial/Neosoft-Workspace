package com.contact.service;

import com.contact.responseBean.ResponseBean;
import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import com.contact.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ResponseBean addContact(Contact contact) {
        Optional<Contact> existingContact = contactRepository.findByContactName(contact.getContactName());
        if (existingContact.isEmpty()) {
            contact.setStatus(ContactStatus.Active);
            contact = contactRepository.save(contact);
            return ResponseBean.builder().message("Contact is saved").status(Boolean.TRUE).data(contact).build();
        } else
            return ResponseBean.builder().message("Contact Name Already exist").status(Boolean.TRUE).build();
    }

    public ResponseBean findAllContact() {
        List<Contact> contacts = contactRepository.findAll();
        if (contacts.isEmpty())
            return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();

        return ResponseBean.builder().status(Boolean.TRUE).data(contacts).build();
    }

    public ResponseBean findContactById(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent())
            return ResponseBean.builder().status(Boolean.TRUE).data(contact.get()).build();
        return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();
    }

    public ResponseBean updateContact(Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findById(contact.getContactId());
        if (optionalContact.isEmpty())
            return ResponseBean.builder().message("No Contact found to update").status(Boolean.TRUE).build();
        contact = contactRepository.saveAndFlush(optionalContact.get());
        return ResponseBean.builder().message("Contact has been updated Successfully").status(Boolean.TRUE).data(contact).build();
    }

    public ResponseBean deleteContact(Long contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isEmpty())
            return ResponseBean.builder().message("No Contact found to delete").status(Boolean.TRUE).build();
        Contact contact = optionalContact.get();
        contact.setStatus(ContactStatus.Deleted);
        contact = contactRepository.saveAndFlush(contact);
        return ResponseBean.builder().message("Contact has been Deleted Successfull").status(Boolean.TRUE).data(contact).build();
    }

    public ResponseBean testing() {
        List<Contact.IContactInfo> byUserId = this.contactRepository.findByUserId();
        return ResponseBean.builder().data(byUserId).build();
    }

}
