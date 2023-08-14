package com.example.pirat.service;

import com.example.pirat.entity.Item;
import com.example.pirat.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface GetDataService {
    Mono<?> getInfoAboutMe(User user);
    Mono<List<?>> getPriceList(Item item);

}
