package com.travel.user.management.controller;

import com.travel.user.management.bean.response.ServiceResponseBean;
import com.travel.user.management.global.exceptional.CannotBeNullException;
import com.travel.user.management.global.exceptional.DoesNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(CannotBeNullException.class)
    public ServiceResponseBean CannotBeNullExceptionHandler(CannotBeNullException cannotBeNullException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(cannotBeNullException.getMessage()).build();
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ServiceResponseBean DoesNotExistExceptionHandler(DoesNotExistException doesNotExistException){
        return ServiceResponseBean.builder().status(Boolean.FALSE).error(doesNotExistException.getMessage()).build();
    }

}
