package com.contact;

import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.dto.ResponseBean.ContactResponseBean;
import com.contact.entity.Contact;
import com.contact.mapper.ContactMapper;
import com.contact.repository.ContactRepository;
import com.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ContactServiceTest {


    ContactRepository mainModel =  Mockito.mock(ContactRepository.class);

    ContactRequestBean contactRequestBean;

    @Mock
    ContactRepository contactRepository;

    @Mock
    ContactRepository contactRepository1;

    @Spy
    ContactMapper contactMapper;

    @InjectMocks
    ContactService contactService;

    @BeforeEach
    public void setUp() throws Exception {
        contactRequestBean = new ContactRequestBean().builder().contactName("Ashay").number("1234567890").build();
    }

    @Test
    public void testAddContact() {

        Mockito.when(mainModel.findByContactName(Mockito.any(String.class))).thenReturn(Optional.empty());
        Mockito.when(mainModel.saveAndFlush(Mockito.any(Contact.class))).thenReturn(contactMapper.requestEntityMapperCreate(contactRequestBean));
        ContactResponseBean contactResponseBeanRecieved = (ContactResponseBean) contactService.addContact(contactRequestBean).data;
        assertEquals(contactRequestBean.getContactName(), contactResponseBeanRecieved.getContactName());
        assertEquals(contactRequestBean.getNumber(), contactResponseBeanRecieved.getNumber());
    }


}
