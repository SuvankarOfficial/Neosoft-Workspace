package com.contact.repository;

import com.contact.entity.Contact;
import com.contact.responseBean.ResponseBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByContactName(String contactName);

    @Query("Select c.contactName AS contactName, p.projectName AS projectName, u.userName AS userName , u.number AS userNumber, c.number AS contactNumber from Contact c, User u, Project p where c.contactId = u.contactId and c.project.projectId = p.projectId")
    List<Contact.IContactInfo> findByUserId();
}
