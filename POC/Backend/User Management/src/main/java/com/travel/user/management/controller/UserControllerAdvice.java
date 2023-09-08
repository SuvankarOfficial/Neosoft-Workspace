package com.travel.user.management.controller;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.global.exceptional.CannotBeNullException;
import com.travel.user.management.global.exceptional.DoesNotExistException;
import com.travel.user.management.global.exceptional.InvalidEmailIdException;
import com.travel.user.management.global.exceptional.InvalidOtpException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(CannotBeNullException.class)
    public ServiceResponseBean cannotBeNullExceptionHandler(CannotBeNullException cannotBeNullException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(cannotBeNullException.getMessage()).build();
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ServiceResponseBean doesNotExistExceptionHandler(DoesNotExistException doesNotExistException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(doesNotExistException.getMessage()).build();
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ServiceResponseBean invalidOtpExceptionHandler(InvalidOtpException invalidOtpException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(invalidOtpException.getMessage()).build();
    }

    @ExceptionHandler(InvalidEmailIdException.class)
    public ServiceResponseBean invalidEmailIdExceptionHandler(InvalidEmailIdException invalidEmailIdException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(invalidEmailIdException.getMessage()).build();
    }

}
