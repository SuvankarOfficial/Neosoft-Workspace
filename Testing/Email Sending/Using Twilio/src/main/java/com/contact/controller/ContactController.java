package com.contact.controller;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.service.ContactService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/")
    public ResponseBean addContact(@RequestBody ContactRequestBean contact){
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
    public ResponseBean updateContact(@RequestBody ContactRequestBean contact){
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/{contact_id}")
    public ResponseBean deleteContact(@PathVariable("contact_id") Long contactId){
        return contactService.deleteContact(contactId);
    }

    @GetMapping(value = "/sendSMS/{number}")
    public ResponseEntity<String> sendSMS(@PathVariable ("number") String number) {
        return this.contactService.sendSMS(number);
    }

}
