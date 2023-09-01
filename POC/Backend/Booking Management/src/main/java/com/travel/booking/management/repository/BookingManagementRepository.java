package com.travel.booking.management.repository;

import com.travel.booking.management.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingManagementRepository extends JpaRepository<BookingEntity, Long> {
}
