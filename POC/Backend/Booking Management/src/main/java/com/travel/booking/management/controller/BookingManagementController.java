package com.travel.booking.management.controller;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingManagementEntity;
import com.travel.booking.management.repository.BookingManagementRepository;
import com.travel.booking.management.service.IBookingManagementService;
import feign.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/booking-management")
public class BookingManagementController {

    @Autowired
    private IBookingManagementService bookingManagementService;

    @GetMapping("/find-by-id/{bookingManagementId}")
    public ServiceResponseBean findById(@PathVariable("bookingManagementId") Long bookingManagementId) {
        return this.bookingManagementService.findById(bookingManagementId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.bookingManagementService.findAll();
    }

    @PostMapping("/add")
    public ServiceResponseBean addBookingManagement(@RequestBody BookingManagementEntity bookingManagement) {
        return this.bookingManagementService.addBookingManagement(bookingManagement);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateBookingManagement(@RequestBody BookingManagementEntity bookingManagement) {
        return this.bookingManagementService.updateBookingManagement(bookingManagement);
    }

    @DeleteMapping("/delete/{bookingManagementId}")
    public ServiceResponseBean deleteBookingManagement(@PathVariable("bookingManagementId") Long bookingManagementId) {
        return this.bookingManagementService.deleteBookingManagement(bookingManagementId);
    }

}
