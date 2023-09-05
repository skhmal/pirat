package com.example.pirat.entity.search;

import lombok.Data;

@Data
public class SearchRequest {
    private int limit;
    private int offset;
    private Where where;

}
