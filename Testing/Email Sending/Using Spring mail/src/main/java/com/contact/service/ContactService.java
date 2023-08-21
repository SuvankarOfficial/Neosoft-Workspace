package com.contact.service;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import com.contact.mapper.ContactMapper;
import com.contact.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContactMapper contactMapper;

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
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("jankit260+springbootmailtesting@gmail.com");
        simpleMailMessage.setSubject("Contact Service");
        simpleMailMessage.setText("Some One tried to fetch All Data");
        simpleMailMessage.setCc("aditya.manjrekar@neosoftmail.com");
        javaMailSender.send(simpleMailMessage);
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

}
