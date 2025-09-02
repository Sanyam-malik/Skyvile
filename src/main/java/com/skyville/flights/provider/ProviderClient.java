package com.skyville.flights.provider;


import com.skyville.flights.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface ProviderClient {
    /**
     * Query provider for flight offers from -> to on a date.
     * Implementations should return offers in provider's native currency;
     * caller will convert/normalize as necessary.
     */
    List<Flight> search(String from, String to, LocalDate date, int adults);
    String name();
}
