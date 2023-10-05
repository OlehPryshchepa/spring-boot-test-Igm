package com.example.springboottesttask.service.impl;

import com.example.springboottesttask.service.HtmlBuilderService;
import com.example.springboottesttask.service.HtmlGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class HtmlBuilderServiceImpl implements HtmlBuilderService {
    private final HtmlGeneratorService htmlGeneratorService;

    @Override
    public String buildHtml() {
        CompletableFuture<String> startHtmlFuture = htmlGeneratorService.generateStartHtml();
        CompletableFuture<String> contentHtmlFuture = htmlGeneratorService.generateContentHtml();
        CompletableFuture<String> endHtmlFuture = htmlGeneratorService.generateEndHtml();

        return CompletableFuture.allOf(startHtmlFuture, contentHtmlFuture, endHtmlFuture)
                .thenApplyAsync(ignored -> {
                    try {
                        String startHtml = startHtmlFuture.get();
                        String contentHtml = contentHtmlFuture.get();
                        String endHtml = endHtmlFuture.get();
                        return startHtml + contentHtml + endHtml;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException("Error occurred while executing CompletableFuture",e);
                    }
                }).join();
    }
}
