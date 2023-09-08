package com.email.otpservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private Map<String,String> otps = new HashMap<>();

    @Autowired
    private JavaMailSender javaMailSender;

    public Boolean sendOtp(String email) {
        String otp = getRandomNumberString();
        Boolean sendStatus = sendMail(email, otp);
        this.otps.put(email,otp);
        return sendStatus;
    }

    private Boolean sendMail(String email, String otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        if(otp == null)
            simpleMailMessage.setSubject("Invalid Email: " + email);
        else
            simpleMailMessage.setSubject("Travel Karo - OTP");
        simpleMailMessage.setText("The otp for travel karo is " + otp);
//        simpleMailMessage.setCc("aditya.manjrekar@neosoftmail.com");
        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            sendMail("jankit260+travel-karo@gmail.com",null);
            return false;
        }
        return true;
    }

    public String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public Boolean verifyOtp(String email, String otp) {
        String otpFromOtps = this.otps.get(email);
        if(otpFromOtps == null)
            return false;
        return (otp.equals(otpFromOtps));

    }
}
