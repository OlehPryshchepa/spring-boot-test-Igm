package com.example.springboottesttask.controller;

import com.example.springboottesttask.dto.ThirdApiResponseDto;
import com.example.springboottesttask.service.MockThirdPartyApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Third-party-api controller", description = "a controller which has method for get " +
        "data from third api with exponential backoff mechanism")
@RestController
@RequiredArgsConstructor
public class ThirdPartyApiController {
    private final MockThirdPartyApiService mockThirdPartyApiService;

    @Operation(summary = "request to a third-party API", description = "allows you to get data from a third-party API")
    @GetMapping("/third-party-api")
    public ThirdApiResponseDto getData() {
        return mockThirdPartyApiService.callThirdPartyApi();
    }
}
