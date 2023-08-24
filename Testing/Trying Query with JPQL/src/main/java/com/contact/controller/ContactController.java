package com.contact.controller;

import com.contact.responseBean.ResponseBean;
import com.contact.entity.Contact;
import com.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/")
    public ResponseBean addContact(@RequestBody Contact contact){
        return contactService.addContact(contact);
    }

    @GetMapping("/")
    public ResponseBean findAllContacts(){
        return contactService.findAllContact();
    }

    @GetMapping("/{contact_id}")
    public ResponseBean findContactById(@PathVariable("contact_id") Long contactId){
        return contactService.findContactById(contactId);
    }

    @PutMapping("/")
    public ResponseBean updateContact(@RequestBody Contact contact){
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/{contact_id}")
    public ResponseBean deleteContact(@PathVariable("contact_id") Long contactId){
        return contactService.deleteContact(contactId);
    }

    @GetMapping("/testing")
    public ResponseBean testing(){
        return contactService.testing();
    }

}
