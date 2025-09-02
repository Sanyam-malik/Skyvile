# Welcome to Skyvile ✈️

Skyvile is a global flight search platform designed to help travelers find the **best flight deals**.  
It aggregates data from multiple providers, normalizes currencies, and ranks results by **price**, **duration**, or **stops**.

Imagine you’re planning a trip: different airlines and agencies may list wildly different prices for the same route.  
Skyvile takes away the guesswork by comparing providers side by side.

---

# The Story Behind Skyvile

There are many providers in the flight booking market. Each one sells tickets with its own rules, prices, and even currencies.

Skyvile’s mission is **not** to sell tickets directly, but to empower travelers with data — showing them the cheapest, fastest, and most convenient flights, regardless of where the ticket is sold.

You’ve joined the Skyvile development team.  
The current goal: **produce a reliable API** that other apps, websites, or even travel chatbots can use to power their search experience.

Some teammates are away, but this is your chance to shape Skyvile’s foundation and deliver something impactful.

---

## Providers

For now, Skyvile connects to two mocked providers (with deterministic offers for demo purposes):

- **ProviderA** – returns flights in `USD`
- **ProviderB** – returns flights in `EUR`

The architecture allows easily adding more providers or swapping mocks with real airline APIs later.

---

## Requirements

- [Java 17](https://adoptium.net/) or newer
- Gradle 8+ (you can use the included [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html))

If you use multiple JVMs, consider a version manager such as [sdkman](https://sdkman.io/).

---

## Useful Gradle Commands

### List all tasks
```bash
./gradlew tasks
```

### Build the project
```bash
./gradlew build
```

### Run the tests
```bash
./gradlew test
```

### Run the application
```bash
./gradlew bootRun
```

The application will be available on [http://localhost:8080](http://localhost:8080).

---

## API

Below is a list of endpoints with input and output examples.  
Make sure the application is running before trying these.

---

### Search Flights

Endpoint:
```text
GET /api/v1/flights/search
```

Parameters:

| Parameter   | Description                                          |
|-------------|------------------------------------------------------|
| `from`      | Origin airport code (e.g. `JFK`)                     |
| `to`        | Destination airport code (e.g. `LAX`)                |
| `date`      | Departure date (ISO format, e.g. `2025-09-20`)       |
| `adults`    | Number of passengers (default: `1`)                  |
| `currency`  | Normalize results to this currency (e.g. `USD`, `EUR`, `INR`) |
| `sort`      | Sort strategy: `price`, `duration`, or `stops` (default: `price`) |

---

### Example: Cheapest Flights
```bash
curl "http://localhost:8080/api/v1/flights/search?from=JFK&to=LAX&date=2025-09-20"
```

### Example: Sorted by Duration
```bash
curl "http://localhost:8080/api/v1/flights/search?from=JFK&to=LAX&date=2025-09-20&sort=duration"
```

### Example: Results in EUR
```bash
curl "http://localhost:8080/api/v1/flights/search?from=JFK&to=LAX&date=2025-09-20&currency=EUR"
```

---

### Sample Output
```json
[
  {
    "provider": "ProviderA",
    "id": "a21f0b8e-1234",
    "from": "JFK",
    "to": "LAX",
    "departure": "2025-09-20T06:30:00",
    "arrival": "2025-09-20T08:40:00",
    "price": 150.0,
    "currency": "USD",
    "cabinClass": "ECONOMY",
    "stops": 0
  },
  {
    "provider": "ProviderB",
    "id": "d3f6c8e2-5678",
    "from": "JFK",
    "to": "LAX",
    "departure": "2025-09-20T09:15:00",
    "arrival": "2025-09-20T11:20:00",
    "price": 156.8,
    "currency": "USD",
    "cabinClass": "ECONOMY",
    "stops": 0
  }
]
```

---

## Design Notes

- **ProviderClient interface** abstracts away external providers.
- **CurrencyConverter** handles all currency normalization.
- **Service layer** aggregates and sorts results across providers.
- **Mock providers** return deterministic values for repeatable testing.
- **Extensibility**: add new providers, currencies, or sorting strategies easily.

---

## License
MIT – free to use.
