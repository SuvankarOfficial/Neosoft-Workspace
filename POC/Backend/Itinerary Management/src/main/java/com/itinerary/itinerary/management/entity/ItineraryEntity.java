package com.itinerary.itinerary.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("itinerary")
public class ItineraryEntity {
    @Id
    private String itineraryUniqueId;

    private String createdByUserUniqueId;

    private String createdForExperienceUniqueId;

    private String name;

    private String description;

    private Boolean status;

}
