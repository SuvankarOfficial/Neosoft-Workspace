package com.itinerary.itinerary.management.controller;

import com.itinerary.itinerary.management.bean.response.ServiceResponseBean;
import com.itinerary.itinerary.management.global.exceptional.CannotBeNullException;
import com.itinerary.itinerary.management.global.exceptional.DoesNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItineraryGlobalExceptionHandler {

    @ExceptionHandler(CannotBeNullException.class)
    public ServiceResponseBean CannotBeNullExceptionHandler(CannotBeNullException cannotBeNullException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(cannotBeNullException.getMessage()).build();
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ServiceResponseBean DoesNotExistExceptionHandler(DoesNotExistException doesNotExistException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(doesNotExistException.getMessage()).build();
    }

}
