package com.travel.user.management.global.exceptional;

public class InvalidOtpException extends RuntimeException {

    public InvalidOtpException(){
        super("Wrong OTP");
    }

}
