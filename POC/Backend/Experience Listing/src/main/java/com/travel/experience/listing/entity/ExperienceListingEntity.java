package com.travel.experience.listing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experience_listing")
public class ExperienceListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "el_id", nullable = false)
    private Long elId;

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

    @Lob()
    @Column(name = "el_image", length = 5000)
    private byte[] image;
}
