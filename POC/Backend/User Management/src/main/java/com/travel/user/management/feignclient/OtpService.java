package com.travel.user.management.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("OTP-SERVICE")
public interface OtpService {

    @PostMapping("/otp/generate-otp")
    public Boolean sendOtp(@RequestBody String email);

    @GetMapping("/otp/verify-otp")
    public Boolean verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp);

}
