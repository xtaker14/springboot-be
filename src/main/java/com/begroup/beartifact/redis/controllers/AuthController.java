package com.begroup.beartifact.redis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.begroup.beartifact.redis.services.OtpService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/requestOtp")
    public String requestOtp(@RequestParam String username) {
        String otp = otpService.generateOtp(username);

        return "OTP Created : " + otp;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(username, otp);
        if (isValid) {
            return "Login successful";
        } else {
            return "Invalid OTP";
        }
    }
}
