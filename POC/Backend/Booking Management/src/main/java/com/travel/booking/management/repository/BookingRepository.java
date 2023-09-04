package com.travel.booking.management.repository;

import com.travel.booking.management.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Optional<BookingEntity> findByBookingUniqueId(String bookingUniqueId);
}
