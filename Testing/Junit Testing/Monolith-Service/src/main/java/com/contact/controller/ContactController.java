package com.contact.controller;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.entity.Contact;
import com.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact){
        return contactService.addContact(contact);
    }

    @GetMapping("/")
    public List<Contact> findAllContacts(){
        return contactService.findAllContact();
    }

    @GetMapping("/{contact_id}")
    public Contact findContactById(@PathVariable("contact_id") Long contactId){
        return contactService.findContactById(contactId);
    }

    @PutMapping("/")
    public Contact updateContact(@RequestBody Contact contact){
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/{contact_id}")
    public Contact deleteContact(@PathVariable("contact_id") Long contactId){
        return contactService.deleteContact(contactId);
    }

}
