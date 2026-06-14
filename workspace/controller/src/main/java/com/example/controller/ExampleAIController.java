package com.example.controller.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@Slf4j
@RequestMapping("/ai/**")
public class ExampleAIController {
    private final WebClient webClient = WebClient.create("http://localhost:8000");
}
