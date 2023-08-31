package com.travel.booking.management.service;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingManagementEntity;

public interface IBookingManagementService {


    public ServiceResponseBean findById(Long bookingManagementId);

    public ServiceResponseBean findAll();

    public ServiceResponseBean addBookingManagement(BookingManagementEntity bookingManagement);

    public ServiceResponseBean updateBookingManagement(BookingManagementEntity bookingManagement);

    public ServiceResponseBean deleteBookingManagement(Long bookingManagementId);
}
