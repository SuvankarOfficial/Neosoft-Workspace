package com.contact.repository;

import com.contact.entity.Contact;
import com.contact.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<Contact> findByContactName(String contactName);

//    @Query("Select ")
//    ResponseBean findByUserId();
}
