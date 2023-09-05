package com.example.pirat.controller;

import com.example.pirat.entity.User;
import com.example.pirat.entity.search.SearchRequest;
import com.example.pirat.entity.search.Where;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/as")
public class Main {

    @Value("${bitskins.api-url}")
    private String URL;

    @Value("${bitskins.key}")
    private String KEY;

    private final WebClient client = WebClient.builder()
            .baseUrl("https://api.bitskins.com")
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(30000000))
            .build();

    @GetMapping("/me")
    public Mono<?> getInfoAboutMe() {
        return client.get()
                .uri("/account/profile/me")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .retrieve()
                .bodyToMono(User.class);
    }

    @GetMapping("/getPriceList/{request}")
    public Flux<Object> getPriceList(@PathVariable String request) {
        Where criteria = new Where();
        criteria.setPrice_from(0);
        criteria.setPrice_to(Integer.MAX_VALUE);
        criteria.setSkin_name("%empress%");
        criteria.setTradehold_to(5);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setLimit(100);
        searchRequest.setOffset(0);
        searchRequest.setWhere(criteria);

        Flux<Object> exchange = client.get()
                .uri("/market/skin/730")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .retrieve()
                .bodyToFlux(Object.class);

        return exchange;
    }

    @PostMapping("/getPriceList/{request}")
    public Flux<Object> getPriceListForParticularItem(@PathVariable String request) {
        Where criteria = new Where();
        criteria.setPrice_from(0);
        criteria.setPrice_to(99999);
        criteria.setSkin_name("%glock%");
        criteria.setTradehold_to(5);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setLimit(30);
        searchRequest.setOffset(0);
        searchRequest.setWhere(criteria);

        String data = "{\"limit\":30,\"offset\":0,\"where\":{\"price_from\":1000,\"price_to\":5000,\"skin_name\":\"%usp%\",\"tradehold_to\":5}}";

        Flux<Object> exchange = client.post()
                .uri("/market/search/730")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .bodyToFlux(Object.class);

        return exchange;
    }
}
