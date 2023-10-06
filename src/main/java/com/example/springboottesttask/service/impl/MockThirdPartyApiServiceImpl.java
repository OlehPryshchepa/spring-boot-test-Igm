package com.example.springboottesttask.service.impl;

import com.example.springboottesttask.dto.ThirdApiResponseDto;
import com.example.springboottesttask.annotation.RetryOnRateLimit;
import com.example.springboottesttask.service.MockThirdPartyApiService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MockThirdPartyApiServiceImpl implements MockThirdPartyApiService {
    private int callCount = 0;
    private static final int INITIAL_DELAY = 2000;
    private static final int INITIAL_RETRY_COUNT = 5;

    @RetryOnRateLimit(maxRetryCount = INITIAL_RETRY_COUNT, initialDelayMs = INITIAL_DELAY)
    public ThirdApiResponseDto callThirdPartyApi() {
        callCount++;
        if (callCount != INITIAL_RETRY_COUNT) {
            return new ThirdApiResponseDto("Rate Limit Exceeded", HttpStatus.TOO_MANY_REQUESTS);
        } else {
            callCount = 0;
            return new ThirdApiResponseDto("OK", HttpStatus.OK);
        }
    }
}
