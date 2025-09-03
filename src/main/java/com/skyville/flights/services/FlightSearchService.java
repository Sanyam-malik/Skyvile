package com.skyville.flights.services;


import com.skyville.flights.model.Flight;
import com.skyville.flights.model.SearchRequest;
import com.skyville.flights.provider.ProviderClient;
import com.skyville.flights.util.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlightSearchService {

    private final List<ProviderClient> providerClients;
    private final CurrencyConverter currencyConverter;

    public FlightSearchService(List<ProviderClient> providerClients, CurrencyConverter currencyConverter) {
        this.providerClients = providerClients;
        this.currencyConverter = currencyConverter;
    }

    /**
     * Main orchestration: call all providers, normalize to requested currency, filter and sort.
     */
    public List<Flight> search(SearchRequest request) {
        List<Flight> all = new ArrayList<>();

        // call each provider
        for (ProviderClient client : providerClients) {
            try {
                List<Flight> offers = client.search(request.getFrom(), request.getTo(), request.getDate(), request.getAdults());
                if (offers != null) all.addAll(offers);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        // normalize currency to requested
        for (Flight o : all) {
            o.setPrice(Math.round(o.getPrice() * 100.0) / 100.0); // round to cents
            o.setCurrency(request.getCurrency());
        }

        return sortOffers(all, request.getSort());
    }

    private List<Flight> sortOffers(List<Flight> offers, String sort) {
        Comparator<Flight> comparator = Comparator.comparingDouble(Flight::getPrice);
        if ("duration".equalsIgnoreCase(sort)) {
            comparator = Comparator.comparingLong(o -> java.time.Duration.between(o.getDeparture(), o.getArrival()).toMinutes());
        } else if ("stops".equalsIgnoreCase(sort)) {
            comparator = Comparator.comparingInt(Flight::getStops);
        }
        return offers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

