package com.skyville.flights.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchRequest {
    private String from;
    private String to;
    private LocalDate date;
    private int adults = 1;
    private String currency = "USD";
    private String sort = "price"; // price, duration, stops
}
