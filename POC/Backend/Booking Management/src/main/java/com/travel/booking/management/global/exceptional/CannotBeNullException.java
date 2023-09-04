package com.travel.booking.management.global.exceptional;

public class CannotBeNullException extends RuntimeException{

    public CannotBeNullException(String property){
        super(property + " cannot be null.");
    }

}
