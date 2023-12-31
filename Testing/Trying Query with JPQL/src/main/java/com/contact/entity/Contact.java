package com.contact.entity;

import com.contact.enums.ContactStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_number")
    private String number;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Project project;

    private ContactStatus status;

    public interface IContactInfo {
        String getContactName();
        String getUserName();

        String getProjectName();

        String getContactNumber();

        String getUserNumber();

    }

}
