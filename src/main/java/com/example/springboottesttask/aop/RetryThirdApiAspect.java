package com.example.springboottesttask.aop;

import com.example.springboottesttask.annotation.RetryOnRateLimit;
import com.example.springboottesttask.dto.ThirdApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RetryThirdApiAspect {
    private static final int BACKOFF_TIME = 2;

    @Around("@annotation(retryAnnotation)")
    public ThirdApiResponseDto retryRequest(ProceedingJoinPoint joinPoint,
                                            RetryOnRateLimit retryAnnotation) throws Throwable {
        int maxRetryCount = retryAnnotation.maxRetryCount();
        int delay = retryAnnotation.initialDelayMs();

        if (maxRetryCount < 0) {
            throw new IllegalArgumentException("maxRetryCount must be greater than or equal to 1");
        }

        int retryCount = 0;

        while (retryCount <= maxRetryCount) {
            ThirdApiResponseDto response = (ThirdApiResponseDto) joinPoint.proceed();
            if (response.getStatus() == HttpStatus.OK) {
                return response;
            } else if (response.getStatus() == HttpStatus.TOO_MANY_REQUESTS) {
                log.info("Third api response with: status code = " + response.getStatus() + " | "
                        + " data: " + response.getData() + ". Retrying in " + delay + "ms...");
                Thread.sleep(delay);
                delay *= BACKOFF_TIME;
                retryCount++;
            } else {
                log.info("Unexpected response: " + response.getStatus());
                return response;
            }
        }

        log.info("Max retry attempts reached. Giving up.");
        return new ThirdApiResponseDto("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


