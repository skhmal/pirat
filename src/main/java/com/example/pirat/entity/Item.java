package com.example.pirat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String name;
    private long price;

    @JsonProperty("float_value")
    private float floatValue;

    @JsonProperty("skin_id")
    private long skinId;

    @JsonProperty("stickers")
    private List<Sticker> stickers;

    private static class Sticker {
        String name;
        Integer slot;
        Integer wear;
        Long skin_id;
        Long class_id;
    }

}