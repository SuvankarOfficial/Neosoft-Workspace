package com.travel.booking.management.controller;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;
import com.travel.booking.management.service.IBookingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ServiceResponseBean addBookingManagement(@RequestBody BookingEntity bookingManagement) {
        return this.bookingManagementService.addBookingManagement(bookingManagement);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateBookingManagement(@RequestBody BookingEntity bookingManagement) {
        return this.bookingManagementService.updateBookingManagement(bookingManagement);
    }

    @DeleteMapping("/delete/{bookingManagementId}")
    public ServiceResponseBean deleteBookingManagement(@PathVariable("bookingManagementId") Long bookingManagementId) {
        return this.bookingManagementService.deleteBookingManagement(bookingManagementId);
    }

}
