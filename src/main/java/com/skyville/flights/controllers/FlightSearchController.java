package com.skyville.flights.controllers;

import com.skyville.flights.model.Flight;
import com.skyville.flights.model.SearchRequest;
import com.skyville.flights.services.FlightSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightSearchController {

    private final FlightSearchService searchService;

    public FlightSearchController(FlightSearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Example:
     * GET /api/v1/flights/search?from=JFK&to=LAX&date=2025-09-20&adults=1&currency=USD&sort=price
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> search(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "1") int adults,
            @RequestParam(defaultValue = "USD") String currency,
            @RequestParam(defaultValue = "price") String sort
    ) {
        SearchRequest req = new SearchRequest();
        req.setFrom(from);
        req.setTo(to);
        req.setDate(date);
        req.setAdults(adults);
        req.setCurrency(currency);
        req.setSort(sort);

        List<Flight> results = searchService.search(req);
        return ResponseEntity.ok(results);
    }
}

