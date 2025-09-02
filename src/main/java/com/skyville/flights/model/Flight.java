package com.skyville.flights.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    private String provider; // which source provided this offer
    private String id; // provider-specific id
    private String from;
    private String to;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price; // normalized currency (e.g. USD)
    private String currency;
    private String cabinClass; // ECONOMY, PREMIUM, etc.
    private int stops;


}
