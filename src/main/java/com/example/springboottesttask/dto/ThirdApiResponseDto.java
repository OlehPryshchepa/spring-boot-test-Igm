package com.example.springboottesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThirdApiResponseDto {
    private int status;
    private String data;
}
