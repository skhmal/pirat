package com.example.pirat.controller;

import com.example.pirat.entity.Response;
import com.example.pirat.entity.User;
import com.example.pirat.entity.search.SearchRequest;
import com.example.pirat.entity.search.Where;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/as")
@Log
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

    @GetMapping("/test")
    public Mono<Void> get1() throws URISyntaxException {
        String apiKey = "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7";
        String socketUrl = "wss://ws.bitskins.com";
        HttpHeaders headers = new HttpHeaders();
        headers.add("WS_AUTH_APIKEY", apiKey);
        headers.add("WS_SUB", "listed");
        headers.add("WS_SUB", "price_changed");

        WebSocketClient client = new ReactorNettyWebSocketClient();
        return client.execute(
                URI.create(socketUrl),
                headers,
                session -> session.receive()
                        .doOnNext(WebSocketMessage::getPayloadAsText)
                        .log()
                        .then()
                        .doOnSuccess(System.out::println)
                        .doOnError(System.out::println)
                        .then());
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
    public Mono<ResponseEntity<List<Response>>> getPriceListForParticularItem(@PathVariable String request) {
//        Where criteria = new Where();
//        criteria.setPrice_from(0);
//        criteria.setPrice_to(99999);
//        criteria.setSkin_name("%glock%");
//        criteria.setTradehold_to(5);
//
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.setLimit(30);
//        searchRequest.setOffset(0);
//        searchRequest.setWhere(criteria);

        String data = "{\"limit\":30,\"offset\":0,\"where\":{\"price_from\":1000,\"price_to\":2000000,\"skin_name\":\"%Neon%Rider%\",\"tradehold_to\":5}}";
        ;

        Mono<ResponseEntity<List<Response>>> exchange = client.post()
                .uri("/market/search/730")
                .header("x-apikey", "01a8eba6c0283b1f60e01aee43118c8858c3c2d0b0fb7916151dcce7d75a2ff7")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .toEntityList(Response.class);

        return exchange;
    }
}
