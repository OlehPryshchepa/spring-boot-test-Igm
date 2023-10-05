package com.example.springboottesttask.service;

import java.util.concurrent.CompletableFuture;

public interface HtmlGeneratorService {
    CompletableFuture<String> generateStartHtml();
    CompletableFuture<String> generateContentHtml();
    CompletableFuture<String> generateEndHtml();
}
