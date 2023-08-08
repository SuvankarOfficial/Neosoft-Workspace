package com.contact.service;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import com.contact.mapper.ContactMapper;
import com.contact.repository.ContactRepository;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CSVService csvService;

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
        return ResponseBean.builder().status(Boolean.TRUE).data(contacts.stream().map(data-> contactMapper.entityResponseMapper(data))).build();
    }

    public Contact findContactById(Long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent())
            return contact.get();
//            return ResponseBean.builder().status(Boolean.TRUE).data(contactMapper.entityResponseMapper(contact.get())).build();
//        CSVPrinter csvPrinter = csvService.writeCSVFile(null, contact.get());
//        return ResponseBean.builder().status(Boolean.TRUE).message("No data Found").build();
        return null;
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

    public void importContact(MultipartFile multipartFile, HttpServletResponse response) {
        try {
            this.csvService.importCSVFile(multipartFile,response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Contact> addUserThroughExcelSheet(List<Contact> contacts){
        List<Contact> existContact =  new ArrayList<>();
        for (Contact contact: contacts) {
            Optional<Contact> existingContact = contactRepository.findByContactName(contact.getContactName());
            if(existingContact.isEmpty()) {
                contact.setStatus(ContactStatus.Active);
                contactRepository.saveAndFlush(contact);
            }
            else
                existContact.add(contact);
        }
        return existContact.isEmpty() ? null : existContact;
    }
}
