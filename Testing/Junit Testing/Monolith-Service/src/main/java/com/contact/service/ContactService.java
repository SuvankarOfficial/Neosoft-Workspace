package com.contact.service;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import com.contact.mapper.ContactMapper;
import com.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    @Autowired
    private final ContactRepository contactRepository;


    public Contact addContact(Contact contact){
        Optional<Contact> existingContact = contactRepository.findByContactName(contact.getContactName());
        if(existingContact.isEmpty()) {
            contact.setStatus(ContactStatus.Active);
            contact = contactRepository.saveAndFlush(contact);
            return contact;
        }
        else
            return null;
    }

    public List<Contact> findAllContact(){
        List<Contact> contacts = contactRepository.findAll();
        if(contacts.isEmpty())
            return null;

        return contacts;
    }

    public Contact findContactById(Long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent())
            return contact.get();
        return null;
    }

    public Contact updateContact(Contact contact){
        Optional<Contact> optionalContact = contactRepository.findById(contact.getContactId());
        if(optionalContact.isEmpty())
            return null;
        contact = optionalContact.get();
        contact = contactRepository.saveAndFlush(contact);
        return contact;
    }

    public Contact deleteContact(Long contactId){
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty())
            return null;
        Contact contact = optionalContact.get();
        contact.setStatus(ContactStatus.Deleted);
        contact = contactRepository.saveAndFlush(contact);
        return contact;
    }

}
