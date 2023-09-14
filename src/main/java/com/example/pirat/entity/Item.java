package com.example.pirat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private long price;

    @JsonProperty("float_value")
    private float floatValue;

    @JsonProperty("skin_id")
    private long skinId;

    @JsonProperty("stickers")
    private List<Stickers> stickers;

}