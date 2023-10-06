package com.example.springboottesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ThirdApiResponseDto {
    private String data;
    private HttpStatus status;
}
