package com.travel.booking.management.service.implementation;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;
import com.travel.booking.management.feign.IExperienceListingService;
import com.travel.booking.management.feign.IUserManagementService;
import com.travel.booking.management.repository.BookingManagementRepository;
import com.travel.booking.management.service.IBookingManagementService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingManagementServiceImpl implements IBookingManagementService {

    @Autowired
    private BookingManagementRepository bookingManagementRepository;

    @Autowired
    private IUserManagementService userManagementService;

    @Autowired
    private IExperienceListingService experienceListingService;

    public ServiceResponseBean findById(Long bookingManagementId) {
        if (bookingManagementId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("bookingManagementId Cannot be null").build();
        Optional<BookingEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagementId);
        if (bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        BookingEntity bookingEntity = bookingManagementEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<BookingEntity> bookingEntityList = this.bookingManagementRepository.findAll();
        if (bookingEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingEntityList).build();
    }

    @CircuitBreaker(name = "booking-management-service", fallbackMethod = "serviceNotAvailable")
    public ServiceResponseBean addBookingManagement(BookingEntity bookingManagement) {
        if (bookingManagement == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement is null").build();
        if(bookingManagement.getUserId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("userID is null").build();
        if(!(this.userManagementService.existByUserManagementUniqueId(bookingManagement.getUserId())))
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("userID doesnot exist").build();
        if(bookingManagement.getExperienceId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Experience ID is null").build();
        if(!(this.experienceListingService.existByExperienceListingUniqueId(bookingManagement.getExperienceId())))
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Experience ID doesnot exist").build();
        bookingManagement.setBookingUniqueId(UUID.randomUUID().toString());
        BookingEntity savedBookingManagement = this.bookingManagementRepository.save(bookingManagement);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

    public ServiceResponseBean serviceNotAvailable(Exception e){
        return ServiceResponseBean.builder().error("Service Not Available, Please try later").build();
    }

    public ServiceResponseBean updateBookingManagement(BookingEntity bookingManagement) {
        if (bookingManagement == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement is null").build();
        if (bookingManagement.getBookingId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement Id is null").build();
        Optional<BookingEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagement.getBookingId());
        if(bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        bookingManagement = updateBookingManagementDatabaseToNewObject(bookingManagement, bookingManagementEntityOptional.get());
        BookingEntity savedBookingManagement = this.bookingManagementRepository.save(bookingManagement);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

    private BookingEntity updateBookingManagementDatabaseToNewObject(BookingEntity bookingManagement, BookingEntity databaseBookingManagement) {
        return BookingEntity.builder()
                .bookingId(databaseBookingManagement.getBookingId())
                .bookingUniqueId(bookingManagement.getBookingUniqueId() != null ? bookingManagement.getBookingUniqueId() : databaseBookingManagement.getBookingUniqueId())
                .userId(bookingManagement.getUserId() != null ? bookingManagement.getUserId() : databaseBookingManagement.getUserId())
                .experienceId(bookingManagement.getExperienceId() != null ? bookingManagement.getExperienceId() : databaseBookingManagement.getExperienceId())
                .bookingDate(bookingManagement.getBookingDate() != null ? bookingManagement.getBookingDate() : databaseBookingManagement.getBookingDate())
                .status(bookingManagement.getStatus() != null ? bookingManagement.getStatus() : databaseBookingManagement.getStatus())
                .paymentStatus(bookingManagement.getPaymentStatus() != null ? bookingManagement.getPaymentStatus() : databaseBookingManagement.getPaymentStatus())
                .totalAmount(bookingManagement.getTotalAmount() != null ? bookingManagement.getTotalAmount() : databaseBookingManagement.getTotalAmount())
                .participantsCount(bookingManagement.getParticipantsCount() != null ? bookingManagement.getParticipantsCount() : databaseBookingManagement.getParticipantsCount())
                .build();
    }

    public ServiceResponseBean deleteBookingManagement(Long bookingManagementId) {
        if (bookingManagementId == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement Id is null").build();
        Optional<BookingEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagementId);
        if(bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        BookingEntity bookingEntity = bookingManagementEntityOptional.get();
        bookingEntity.setStatus("Cancelled");
        BookingEntity savedBookingManagement = this.bookingManagementRepository.save(bookingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

}
