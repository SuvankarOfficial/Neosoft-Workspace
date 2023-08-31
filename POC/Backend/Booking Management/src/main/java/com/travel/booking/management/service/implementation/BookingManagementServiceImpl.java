package com.travel.booking.management.service.implementation;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingManagementEntity;
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
        Optional<BookingManagementEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagementId);
        if (bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        BookingManagementEntity bookingManagementEntity = bookingManagementEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingManagementEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<BookingManagementEntity> bookingManagementEntityList = this.bookingManagementRepository.findAll();
        if (bookingManagementEntityList.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).error("No data").build();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingManagementEntityList).build();
    }

    public ServiceResponseBean addBookingManagement(BookingManagementEntity bookingManagement) {
        if (bookingManagement == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement is null").build();
        if(bookingManagement.getUserId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("userID is null").build();
        if(checkIfUserExist(bookingManagement.getUserId()))
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("userID doesnot exist").build();
        if(bookingManagement.getExperienceId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Experience ID is null").build();
        if(checkIfExperienceExist(bookingManagement.getExperienceId()))
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("Experience ID doesnot exist").build();
        bookingManagement.setBookingManagementUniqueId(UUID.randomUUID().toString());
        BookingManagementEntity savedBookingManagement = this.bookingManagementRepository.save(bookingManagement);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

    @CircuitBreaker(name = "BookingManagementServiceImpl", fallbackMethod = "ErrorConnectingUserManagementService")
    private boolean checkIfUserExist(String userId) {
        Boolean exist = this.userManagementService.existByUserManagementUniqueId(userId);
        return !exist;
    }

    @CircuitBreaker(name = "BookingManagementServiceImpl", fallbackMethod = "backUpMethodForCircuitBreaker")
    private boolean checkIfExperienceExist(String experienceId) {
        return !(this.experienceListingService.existByExperienceListingUniqueId(experienceId));
    }

    public Boolean backUpMethodForCircuitBreaker(Exception e){
        return Boolean.FALSE;
    }

    public ServiceResponseBean ErrorConnectingUserManagementService(Exception e,String experienceId){
        return ServiceResponseBean.builder().error(e.getMessage()).build();
    }

    public ServiceResponseBean updateBookingManagement(BookingManagementEntity bookingManagement) {
        if (bookingManagement == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement is null").build();
        if (bookingManagement.getBookingManagementId() == null)
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("BookingManagement Id is null").build();
        Optional<BookingManagementEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagement.getBookingManagementId());
        if(bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.FALSE).data("No Data to update").build();
        bookingManagement = updateBookingManagementDatabaseToNewObject(bookingManagement, bookingManagementEntityOptional.get());
        BookingManagementEntity savedBookingManagement = this.bookingManagementRepository.save(bookingManagement);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

    private BookingManagementEntity updateBookingManagementDatabaseToNewObject(BookingManagementEntity bookingManagement, BookingManagementEntity databaseBookingManagement) {
        return databaseBookingManagement.builder()
                .bookingManagementId(databaseBookingManagement.getBookingManagementId())
                .bookingManagementUniqueId(bookingManagement.getBookingManagementUniqueId() != null ? bookingManagement.getBookingManagementUniqueId() : databaseBookingManagement.getBookingManagementUniqueId())
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
        Optional<BookingManagementEntity> bookingManagementEntityOptional = this.bookingManagementRepository.findById(bookingManagementId);
        if(bookingManagementEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        BookingManagementEntity bookingManagementEntity = bookingManagementEntityOptional.get();
        bookingManagementEntity.setStatus("Cancelled");
        BookingManagementEntity savedBookingManagement = this.bookingManagementRepository.save(bookingManagementEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBookingManagement).build();
    }

}
