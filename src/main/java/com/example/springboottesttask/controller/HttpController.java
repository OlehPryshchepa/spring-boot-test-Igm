package com.example.springboottesttask.controller;

import com.example.springboottesttask.dto.HtmlResponseDto;
import com.example.springboottesttask.service.HtmlBuilderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Http controller", description = "a controller which has method for generation html page")
@RestController
@RequiredArgsConstructor
public class HttpController {
    private final HtmlBuilderService htmlBuilderService;

    @Operation(summary = "generate an HTML page", description = "allows you to get the generated HTML page from the application in json format")
    @GetMapping(value = "/html/generate")
    public HtmlResponseDto getHtml() {
        return new HtmlResponseDto(htmlBuilderService.buildHtml());
    }
}
