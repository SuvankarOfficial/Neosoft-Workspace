package com.travel.booking.management.service;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;

public interface IBookingManagementService {


    public ServiceResponseBean findById(Long bookingManagementId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addBookingManagement(BookingEntity bookingManagement);

    public ServiceResponseBean updateBookingManagement(BookingEntity bookingManagement);

    public ServiceResponseBean deleteBookingManagement(Long bookingManagementId);
}
