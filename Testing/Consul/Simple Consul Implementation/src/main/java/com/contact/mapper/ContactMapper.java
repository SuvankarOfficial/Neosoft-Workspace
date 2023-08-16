package com.contact.mapper;

import com.contact.dto.RequestBean.ContactRequestBean;
import com.contact.dto.ResponseBean.ContactResponseBean;
import com.contact.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact requestEntityMapperUpdate(ContactRequestBean contactRequestBean, Contact contact){
        return contact.builder()
                .contactId(contactRequestBean.getContactId() != null ? contactRequestBean.getContactId() : contact.getContactId())
                .contactName(contactRequestBean.getContactName() != null ? contactRequestBean.getContactName(): contact.getContactName())
                .number(contactRequestBean.getNumber() != null ? contactRequestBean.getNumber() : contact.getNumber())
                .userId(contactRequestBean.getUserId() != null ? contactRequestBean.getUserId() : contact.getUserId())
                .build();
    }

    public ContactResponseBean entityResponseMapper(Contact contact){
        ContactResponseBean contactResponseBean = null;
        return contactResponseBean.builder()
                .contactName(contact.getContactName())
                .number(contact.getNumber())
                .userId(contact.getUserId())
                .build();
    }

    public Contact requestEntityMapperCreate(ContactRequestBean contactRequestBean) {
        Contact contact = null;
        return contact.builder()
                .contactName(contactRequestBean.getContactName())
                .number(contactRequestBean.getNumber())
                .userId(contactRequestBean.getUserId())
                .build();
    }
}
