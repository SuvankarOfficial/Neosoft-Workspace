package com.contact.controller;

import com.contact.ResponseBean.ResponseBean;
import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.service.CSVService;
import com.contact.service.ContactService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private CSVService csvService;

    @PostMapping("/")
    public ResponseBean addContact(@RequestBody ContactRequestBean contact){
        return contactService.addContact(contact);
    }

    @GetMapping("/")
    public ResponseBean findAllContacts(){
        return contactService.findAllContact();
    }

    @GetMapping("/{contact_id}")
    public void findContactById(@PathVariable("contact_id") Long contactId, HttpServletResponse httpServletResponse) throws IOException {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE,"text/csv");
//        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename("ContactId " + contactId+".csv").build());
//        httpServletResponse.setContentType("text/csv");
//        httpServletResponse.addHeader("Content-Disposition","attachment; filename=\"ContactId " + contactId+".csv");
//        csvService.writeCSVFile(httpServletResponse.getWriter(),this.contactService.findContactById(contactId));
        return;
    }

    @PutMapping("/")
    public ResponseBean updateContact(@RequestBody ContactRequestBean contact){
        return contactService.updateContact(contact);
    }

    @PostMapping("/import")
    public void importContact(@RequestParam MultipartFile multipartFile,HttpServletResponse response){
        contactService.importContact(multipartFile,response);
    }

    @DeleteMapping("/{contact_id}")
    public ResponseBean deleteContact(@PathVariable("contact_id") Long contactId){
        return contactService.deleteContact(contactId);
    }

}
