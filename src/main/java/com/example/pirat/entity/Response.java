package com.example.pirat.entity;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Counter counter;
    private List<Item> list;
}
