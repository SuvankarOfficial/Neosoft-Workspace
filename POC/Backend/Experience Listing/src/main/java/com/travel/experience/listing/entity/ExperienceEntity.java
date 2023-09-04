package com.travel.experience.listing.entity;

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
@Table(name = "experience_listing")
public class ExperienceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "el_id", nullable = false)
    private Long experienceId;

    @Column(name = "el_unique_id", unique = true)
    private String experienceUniqueId;

    @Column(name = "el_name")
    private String name;

    @Column(columnDefinition = "LONGTEXT", name = "el_description")
    private String description;

    @Column(name = "el_location")
    private String location;

    @Column(name = "el_category")
    private String category;

    @Column(name = "el_price")
    private Double price;

    @Column(name = "el_availability")
    private Long availability;

    @Column(name = "el_duration")
    private Long duration;

    @Lob()
    @Column(name = "el_image", length = 16777215)
    private byte[] image;

    @Column(name = "el_image_name")
    private String imageName;

    @Column(name = "el_image_type")
    private String imageType;

    @Column(name = "el_status")
    private Boolean status;
}
