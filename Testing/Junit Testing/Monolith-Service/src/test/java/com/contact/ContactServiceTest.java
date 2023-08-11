package com.contact;

import com.contact.entity.Contact;
import com.contact.repository.ContactRepository;
import com.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ContactServiceTest {



    Contact contact;
    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    @BeforeEach
    public void setUp() throws Exception {
        contact = new Contact().builder().contactName("Ashay").number("1234567890").build();
    }

    @Test
    public void testAddContact() {
        when(contactRepository.findByContactName(Mockito.any(String.class))).thenReturn(Optional.empty());
        when(contactRepository.saveAndFlush(Mockito.any(Contact.class))).thenReturn(contact);
        Contact savedContact = contactService.addContact(contact);
        assertEquals(contact.getContactName(), savedContact.getContactName());
        assertEquals(contact.getNumber(), savedContact.getNumber());
    }

    @Test
    public void testFindAllContact(){
        when(contactRepository.findAll()).thenReturn(List.of());
        List<Contact> contactList = contactService.findAllContact();
        assertTrue(contactList == null);
    }

    @Test
    public void testFindContactById(){
        when(contactRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(contact));
        Contact contactFromRepository = contactService.findContactById(7l);
        assertFalse(contactFromRepository.getContactName().equals("Aditya"));
        assertNotNull(contactFromRepository.getNumber());
        assertNull(contactFromRepository.getContactId());
    }

    @Test
    public void testUpdateContact() {
        when(contactRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(contact));
        when(contactRepository.saveAndFlush(Mockito.any(Contact.class))).thenReturn(contact);
        Contact savedContact = contactService.updateContact(contact.builder().contactId(1l).build());
        assertEquals(contact.getContactName(), savedContact.getContactName());
        assertEquals(contact.getNumber(), savedContact.getNumber());
    }

    @Test
    public void testDeleteContact() {
        when(contactRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(contact));
        when(contactRepository.saveAndFlush(Mockito.any(Contact.class))).thenReturn(contact);
        Contact savedContact = contactService.deleteContact(1l);
        assertEquals(contact.getContactName(), savedContact.getContactName());
        assertEquals(contact.getNumber(), savedContact.getNumber());
    }


}
