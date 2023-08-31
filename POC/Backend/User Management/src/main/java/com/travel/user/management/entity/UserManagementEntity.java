package com.travel.user.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_management")
public class UserManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "um_id", nullable = false)
    private Long userManagementId;

    @Column(name = "um_unique_id")
    private String userManagementUniqueId;

    @Column(name = "um_username")
    private String username;

    @Column(name = "um_password")
    private String password;

    @Column(name = "um_email")
    private String email;

    @Column(name = "um_first_name")
    private String firstName;

    @Column(name = "um_last_name")
    private String lastName;

    @Column(name = "um_role")
    private String role;

    @Column(name = "um_status")
    private Boolean status;

}
