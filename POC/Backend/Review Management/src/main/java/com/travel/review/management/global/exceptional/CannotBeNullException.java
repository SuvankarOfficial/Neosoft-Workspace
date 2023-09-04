package com.travel.review.management.global.exceptional;

public class CannotBeNullException extends RuntimeException{

    public CannotBeNullException (String property){
        super(property + " cannot be null.");
    }

}
