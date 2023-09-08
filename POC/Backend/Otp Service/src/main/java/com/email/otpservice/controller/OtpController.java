package com.email.otpservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate-otp")
    public Boolean sendOtp(@RequestBody String email){
        return this.otpService.sendOtp(email);
    };

    @GetMapping("/verify-otp")
    public Boolean verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp){
        return this.otpService.verifyOtp(email,otp);
    }

}
