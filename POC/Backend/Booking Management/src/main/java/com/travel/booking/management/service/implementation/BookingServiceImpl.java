package com.travel.booking.management.service.implementation;

import com.travel.booking.management.bean.response.ServiceResponseBean;
import com.travel.booking.management.entity.BookingEntity;
import com.travel.booking.management.feign.IExperienceListingService;
import com.travel.booking.management.feign.IUserManagementService;
import com.travel.booking.management.global.exceptional.CannotBeNullException;
import com.travel.booking.management.global.exceptional.DoesNotExistException;
import com.travel.booking.management.repository.BookingRepository;
import com.travel.booking.management.service.IBookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private IUserManagementService userManagementService;

    @Autowired
    private IExperienceListingService experienceListingService;

    public ServiceResponseBean findById(String bookingUniqueId) {
        if (bookingUniqueId == null)
            throw new CannotBeNullException("Booking Id");
        Optional<BookingEntity> bookingEntityOptional = this.bookingRepository.findByBookingUniqueId(bookingUniqueId);
        if (bookingEntityOptional.isEmpty())
            throw new DoesNotExistException("Booking Id");
        BookingEntity bookingEntity = bookingEntityOptional.get();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingEntity).build();
    }

    public ServiceResponseBean findAll() {
        List<BookingEntity> bookingEntityList = this.bookingRepository.findAll();
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(bookingEntityList).build();
    }

    @CircuitBreaker(name = "booking-service", fallbackMethod = "serviceNotAvailable")
    public ServiceResponseBean addBooking(BookingEntity bookingEntity) {
        if (bookingEntity == null)
            throw new CannotBeNullException("Booking Detils");
        if(bookingEntity.getUserId() == null || bookingEntity.getUserId().isBlank())
            throw new CannotBeNullException("User ID");
        if(!(this.userManagementService.existByUserManagementUniqueId(bookingEntity.getUserId())))
            throw new DoesNotExistException("User ID");
        if(bookingEntity.getExperienceId() == null || bookingEntity.getExperienceId().isBlank())
            throw new CannotBeNullException("Experience ID");
        if(!(this.experienceListingService.existByExperienceListingUniqueId(bookingEntity.getExperienceId())))
            throw new DoesNotExistException("Experience ID");
        bookingEntity.setBookingUniqueId(UUID.randomUUID().toString());
        BookingEntity savedBooking = this.bookingRepository.save(bookingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBooking).build();
    }

    public ServiceResponseBean updateBooking(BookingEntity bookingEntity) {
        if (bookingEntity == null)
            throw new CannotBeNullException("Booking");
        if (bookingEntity.getBookingUniqueId() == null)
            throw new CannotBeNullException("Booking Id");
        if(bookingEntity.getUserId() != null)
            if(!(this.userManagementService.existByUserManagementUniqueId(bookingEntity.getUserId())))
                throw new DoesNotExistException("User Id");
        if(bookingEntity.getExperienceId() != null)
            if(!(this.experienceListingService.existByExperienceListingUniqueId(bookingEntity.getExperienceId())))
                throw new DoesNotExistException("Experience ID");
        Optional<BookingEntity> bookingEntityOptional = this.bookingRepository.findByBookingUniqueId(bookingEntity.getBookingUniqueId());
        if(bookingEntityOptional.isEmpty())
            throw new DoesNotExistException("Booking Id");
        bookingEntity = updateBookingManagementDatabaseToNewObject(bookingEntity, bookingEntityOptional.get());
        BookingEntity savedBooking = this.bookingRepository.save(bookingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBooking).build();
    }

    private BookingEntity updateBookingManagementDatabaseToNewObject(BookingEntity bookingEntity, BookingEntity databaseBooking) {
        return BookingEntity.builder()
                .bookingId(databaseBooking.getBookingId())
                .bookingUniqueId(bookingEntity.getBookingUniqueId() != null ? bookingEntity.getBookingUniqueId() : databaseBooking.getBookingUniqueId())
                .userId(bookingEntity.getUserId() != null ? bookingEntity.getUserId() : databaseBooking.getUserId())
                .experienceId(bookingEntity.getExperienceId() != null ? bookingEntity.getExperienceId() : databaseBooking.getExperienceId())
                .bookingDate(bookingEntity.getBookingDate() != null ? bookingEntity.getBookingDate() : databaseBooking.getBookingDate())
                .status(bookingEntity.getStatus() != null ? bookingEntity.getStatus() : databaseBooking.getStatus())
                .paymentStatus(bookingEntity.getPaymentStatus() != null ? bookingEntity.getPaymentStatus() : databaseBooking.getPaymentStatus())
                .totalAmount(bookingEntity.getTotalAmount() != null ? bookingEntity.getTotalAmount() : databaseBooking.getTotalAmount())
                .participantsCount(bookingEntity.getParticipantsCount() != null ? bookingEntity.getParticipantsCount() : databaseBooking.getParticipantsCount())
                .build();
    }

    public ServiceResponseBean deleteBooking(String bookingUniqueId) {
        if (bookingUniqueId == null)
            throw new CannotBeNullException("Booking Id is null");
        Optional<BookingEntity> bookingEntityOptional = this.bookingRepository.findByBookingUniqueId(bookingUniqueId);
        if(bookingEntityOptional.isEmpty())
            return ServiceResponseBean.builder().status(Boolean.TRUE).error("No Data").build();
        BookingEntity bookingEntity = bookingEntityOptional.get();
        bookingEntity.setStatus("Cancelled");
        BookingEntity savedBooking = this.bookingRepository.save(bookingEntity);
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(savedBooking).build();
    }

}
