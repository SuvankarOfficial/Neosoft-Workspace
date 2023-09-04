package com.travel.experience.listing.global.exceptional;

public class DoesNotExistException extends RuntimeException{

    public DoesNotExistException(String property) {
        super(property + " does not exist.");
    }

}
