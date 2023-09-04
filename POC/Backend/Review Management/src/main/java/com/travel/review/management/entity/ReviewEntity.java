package com.travel.review.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("review")
public class ReviewEntity {

    @Id
    private String reviewUniqueId;

    private String userUniqueId;

    private String experienceUniqueId;

    private Long rating;

    private String comment;

    private LocalDate date;

    private Boolean status;

}
