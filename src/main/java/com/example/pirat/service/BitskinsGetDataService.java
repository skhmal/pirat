package com.example.pirat.service;

import com.example.pirat.entity.Item;
import com.example.pirat.entity.Request;
import com.example.pirat.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
public class BitskinsGetDataService implements GetDataService{
    @Value("${bitskins.api-url}")
    private final String URL;

    @Value("${bitskins.key}")
    private final String KEY;

    @Value("${bitskins.search-uri}")
    private final String URI;

    private WebClient client = WebClient.create(URL);

    @Override
    public Mono<?> getInfoAboutMe(User user) {
        return null;
    }

    @Override
    public Mono<List<?>> getPriceList(Item item) {
        Request request = new Request();
//        request.s
//        return client.post()
//                .uri(URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("x-apikey", KEY)
//                .bodyValue();
        return null;
    }
}
