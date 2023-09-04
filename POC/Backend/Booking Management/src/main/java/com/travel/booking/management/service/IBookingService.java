package com.travel.booking.management.service;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;

public interface IBookingService {


    public ServiceResponseBean findById(String bookingUniqueId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addBooking(BookingEntity bookingManagement);

    public ServiceResponseBean updateBooking(BookingEntity bookingManagement);

    public ServiceResponseBean deleteBooking(String bookingUniqueId);
}
