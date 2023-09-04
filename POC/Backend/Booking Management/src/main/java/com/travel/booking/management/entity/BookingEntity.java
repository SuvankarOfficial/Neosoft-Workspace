package com.travel.booking.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "booking_management")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bm_id", nullable = false)
    private Long bookingId;

    @Column(name = "bm_unique_id", unique = true)
    private String bookingUniqueId;

    @Column(name = "bm_user_unique_id")
    private String userId;

    @Column(name = "bm_experience_unique_id")
    private String experienceId;

    @Column(name = "bm_booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "bm_status")
    private String status;

    @Column(name = "bm_payment_status")
    private String paymentStatus;

    @Column(name = "bm_total_amount")
    private Long totalAmount;

    @Column(name = "bm_no_of_participants")
    private String participantsCount;

}
