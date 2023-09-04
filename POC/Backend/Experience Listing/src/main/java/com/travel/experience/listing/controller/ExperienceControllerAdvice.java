package com.travel.experience.listing.controller;

import com.travel.experience.listing.bean.response.ServiceResponseBean;
import com.travel.experience.listing.global.exceptional.CannotBeNullException;
import com.travel.experience.listing.global.exceptional.DoesNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExperienceControllerAdvice {

    @ExceptionHandler(CannotBeNullException.class)
    public ServiceResponseBean CannotBeNullExceptionHandler(CannotBeNullException cannotBeNullException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(cannotBeNullException.getMessage()).build();
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ServiceResponseBean DoesNotExistExceptionHandler(DoesNotExistException doesNotExistException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(doesNotExistException.getMessage()).build();
    }

}
