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
public class ProviderB implements ProviderClient {
    @Override
    public List<Flight> search(String from, String to, LocalDate date, int adults) {
        List<Flight> results = new ArrayList<>();
        // provider returns in EUR
        LocalDateTime dep1 = LocalDateTime.of(date, LocalTime.of(9, 15));
        LocalDateTime arr1 = dep1.plusHours(2).plusMinutes(5);
        results.add(new Flight(name(), UUID.randomUUID().toString(), from, to, dep1, arr1, 140.0, "EUR", "ECONOMY", 0));

        LocalDateTime dep2 = LocalDateTime.of(date, LocalTime.of(19, 0));
        LocalDateTime arr2 = dep2.plusHours(4).plusMinutes(0);
        results.add(new Flight(name(), UUID.randomUUID().toString(), from, to, dep2, arr2, 120.0, "EUR", "ECONOMY", 1));

        return results;
    }

    @Override
    public String name() {
        return "Indigo";
    }
}
