package com.begroup.beartifact.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String generateRandomOtp() {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(randomPin);
    }

    public String generateOtp(String key) {
        String otp = generateRandomOtp();
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, otp, 5, TimeUnit.MINUTES); // OTP akan kadaluarsa setelah 5 menit

        return otp;
    }

    public boolean validateOtp(String key, String submittedOtp) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String storedOtp = values.get(key);
        return submittedOtp.equals(storedOtp);
    }
}
