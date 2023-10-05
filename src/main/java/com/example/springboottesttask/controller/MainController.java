package com.example.springboottesttask.controller;

import com.example.springboottesttask.dto.ThirdApiResponseDto;
import com.example.springboottesttask.dto.HtmlResponseDto;
import com.example.springboottesttask.service.HtmlBuilderService;
import com.example.springboottesttask.service.MockThirdPartyApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Main controller", description = "a controller that contains two methods for obtaining information")
@RestController
@RequiredArgsConstructor
public class MainController {
    private final HtmlBuilderService htmlBuilderService;
    private final MockThirdPartyApiService mockThirdPartyApiService;

    @Operation(summary = "generate an HTML page", description = "allows you to get the generated HTML page from the application")
    @GetMapping(value = "/html/generate")
    public HtmlResponseDto getHtml() {
        return new HtmlResponseDto(htmlBuilderService.buildHtml());
    }

    @Operation(summary = "request to a third-party API", description = "allows you to get data from a third-party API")
    @GetMapping("/api/third-party")
    public ThirdApiResponseDto getData() {
        return mockThirdPartyApiService.callThirdPartyApi();
    }
}

