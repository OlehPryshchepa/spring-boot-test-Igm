package com.example.springboottesttask.service.impl;

import com.example.springboottesttask.dto.ThirdApiResponseDto;
import com.example.springboottesttask.annotation.RetryOnRateLimit;
import com.example.springboottesttask.service.MockThirdPartyApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MockThirdPartyApiServiceImpl implements MockThirdPartyApiService {
    private int callCount = 0;

    @RetryOnRateLimit(maxRetryCount = 12, initialDelayMs = 2)
    public ThirdApiResponseDto callThirdPartyApi() {
        callCount++;
        if (callCount != 12) {
            return new ThirdApiResponseDto(429, "Rate Limit Exceeded");
        } else {
            callCount = 0;
            return new ThirdApiResponseDto(200, "OK");
        }
    }
}
