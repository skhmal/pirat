package com.example.pirat.controller;

import com.example.pirat.entity.Request;
import com.example.pirat.entity.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/as")
public class Main {

    @Value("${bitskins.api-url}")
    private String URL;

    @Value("${bitskins.key}")
    private String KEY;

    private  WebClient client = WebClient.create("https://api.bitskins.com");

    @GetMapping("/me")
    public Mono<?> getInfoAboutMe() {
        return client.get()
                .uri("/account/profile/me")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .retrieve()
                .bodyToMono(User.class);
    }

    @GetMapping("/getPriceList/{model}")
    public Mono<?> getPriceList(@PathVariable Request request) {
        return client.get()
                .uri("/account/profile/me")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .retrieve()
                .bodyToMono(User.class);
    }
}
