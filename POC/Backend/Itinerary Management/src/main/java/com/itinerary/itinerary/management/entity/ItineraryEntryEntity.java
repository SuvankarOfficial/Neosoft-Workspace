package com.itinerary.itinerary.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("itinerary_entry")
public class ItineraryEntryEntity {
    @Id
    private Long itineraryEntryId;

    private String itineraryEntryUniqueId;

    private String itineraryUniqueId;

    private String experienceId;

    private String notes;

    private LocalTime time;

    private LocalDate date;

    private Boolean status;

}
