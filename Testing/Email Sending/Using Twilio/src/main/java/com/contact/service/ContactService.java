package com.contact.service;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import com.contact.mapper.ContactMapper;
import com.contact.repository.ContactRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Value("${twilio.phone}")
    private String phoneNumber;

    @Value("${twilio.SID}")
    private String twilioSID;

    @Value("${twilio.auth-token}")
    private String twilioAuthToken;

    public ResponseBean addContact(ContactRequestBean contactRequestBean){
        Optional<Contact> existingContact = contactRepository.findByContactName(contactRequestBean.getContactName());
        if(existingContact.isEmpty()) {
            Contact contact = contactMapper.requestEntityMapperCreate(contactRequestBean);
            contact.setStatus(ContactStatus.Active);
            contact = contactRepository.saveAndFlush(contact);
            return ResponseBean.builder().message("Contact is saved").status(Boolean.TRUE).data(contactMapper.entityResponseMapper(contact)).build();
        }
        else
            return ResponseBean.builder().message("Contact Name Already exist").status(Boolean.TRUE).build();
    }

    public ResponseBean findAllContact(){
        List<Contact> contacts = contactRepository.findAll();
        if(contacts.isEmpty())
            return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();

        return ResponseBean.builder().status(Boolean.TRUE).data(contacts.stream().map(data-> contactMapper.entityResponseMapper(data))).build();
    }

    public ResponseBean findContactById(Long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent())
            return ResponseBean.builder().status(Boolean.TRUE).data(contactMapper.entityResponseMapper(contact.get())).build();
        return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();
    }

    public ResponseBean updateContact(ContactRequestBean contactRequestBean){
        Optional<Contact> optionalContact = contactRepository.findById(contactRequestBean.getContactId());
        if(optionalContact.isEmpty())
            return ResponseBean.builder().message("No Contact found to update").status(Boolean.TRUE).build();
        Contact contact = contactMapper.requestEntityMapperUpdate(contactRequestBean, optionalContact.get());
        contact = contactRepository.saveAndFlush(contact);
        return ResponseBean.builder().message("Contact has been updated Successfully").status(Boolean.TRUE).data(contactMapper.entityResponseMapper(contact)).build();
    }

    public ResponseBean deleteContact(Long contactId){
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty())
            return ResponseBean.builder().message("No Contact found to delete").status(Boolean.TRUE).build();
        Contact contact = optionalContact.get();
        contact.setStatus(ContactStatus.Deleted);
        contact = contactRepository.saveAndFlush(contact);
        return ResponseBean.builder().message("Contact has been Deleted Successfull").status(Boolean.TRUE).data(contactMapper.entityResponseMapper(contact)).build();
    }

    public ResponseEntity<String> sendSMS(String number) {
//        Twilio.init(twilioSID,twilioAuthToken);
//
//        Message.creator(new PhoneNumber("+"+phoneNumber),
//                new PhoneNumber("+91" + number), "Hello from Suvankar, Twilio Testing").create();
//

        Twilio.init(twilioSID, twilioAuthToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91" + number),
                new com.twilio.type.PhoneNumber("+" + phoneNumber),
                "Hello from Suvankar, Testing Twilio")
                .create();

        return new ResponseEntity<String>(message.toString(), HttpStatus.OK);
    }
}
