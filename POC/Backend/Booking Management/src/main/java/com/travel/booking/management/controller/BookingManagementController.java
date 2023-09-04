package com.travel.booking.management.controller;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;
import com.travel.booking.management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingManagementController {

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/find-by-id/{bookingUniqueId}")
    public ServiceResponseBean findById(@PathVariable("bookingUniqueId") String bookingUniqueId) {
        return this.bookingService.findById(bookingUniqueId);
    }

    @GetMapping("/find-all")
    public ServiceResponseBean findAll() {
        return this.bookingService.findAll();
    }

    @PostMapping("/add")
    public ServiceResponseBean addBooking(@RequestBody BookingEntity booking) {
        return this.bookingService.addBooking(booking);
    }

    @PutMapping("/update")
    public ServiceResponseBean updateBooking(@RequestBody BookingEntity booking) {
        return this.bookingService.updateBooking(booking);
    }

    @DeleteMapping("/delete/{bookingUniqueId}")
    public ServiceResponseBean deleteBooking(@PathVariable("bookingUniqueId") String bookingUniqueId) {
        return this.bookingService.deleteBooking(bookingUniqueId);
    }

}
