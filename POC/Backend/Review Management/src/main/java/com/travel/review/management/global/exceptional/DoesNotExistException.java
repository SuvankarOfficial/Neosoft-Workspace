package com.travel.review.management.global.exceptional;

public class DoesNotExistException extends RuntimeException{

    public DoesNotExistException(String property) {
        super(property + " does not exist.");
    }

}
