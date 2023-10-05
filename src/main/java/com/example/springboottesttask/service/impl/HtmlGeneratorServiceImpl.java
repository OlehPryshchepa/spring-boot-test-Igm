package com.example.springboottesttask.service.impl;

import com.example.springboottesttask.service.HtmlGeneratorService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HtmlGeneratorServiceImpl implements HtmlGeneratorService {
    private ExecutorService executor
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public CompletableFuture<String> generateStartHtml() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "<html><body>";
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while generating "
                        + "the beginning of an HTML page", e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<String> generateContentHtml() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "<h1>Hello, Igm.Technology! Today is %s</h1>".formatted(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while generating "
                        + "HTML page content", e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<String> generateEndHtml() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "</body></html>";
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while generating "
                        + "end of HTML page", e);
            }
        }, executor);
    }
}
