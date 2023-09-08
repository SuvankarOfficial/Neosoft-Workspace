package com.travel.user.management.global.exceptional;

public class InvalidEmailIdException extends RuntimeException {

    public InvalidEmailIdException(){
        super("Invalid Email Id");
    }

}
