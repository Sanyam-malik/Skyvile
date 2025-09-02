package com.skyville.flights.provider;
import com.skyville.flights.model.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProviderA implements ProviderClient {

    @Override
    public List<Flight> search(String from, String to, LocalDate date, int adults) {
        List<Flight> results = new ArrayList<>();
        // deterministic mock offers
        LocalDateTime dep1 = LocalDateTime.of(date, LocalTime.of(6, 30));
        LocalDateTime arr1 = dep1.plusHours(2).plusMinutes(10);
        results.add(new Flight(name(), UUID.randomUUID().toString(), from, to, dep1, arr1, 150.0, "USD", "ECONOMY", 0));

        LocalDateTime dep2 = LocalDateTime.of(date, LocalTime.of(13, 0));
        LocalDateTime arr2 = dep2.plusHours(2).plusMinutes(20);
        results.add(new Flight(name(), UUID.randomUUID().toString(), from, to, dep2, arr2, 180.0, "USD", "ECONOMY", 0));

        return results;
    }

    @Override
    public String name() {
        return "AkasaAir";
    }
}

