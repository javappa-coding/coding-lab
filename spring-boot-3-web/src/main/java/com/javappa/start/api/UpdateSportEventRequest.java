package com.javappa.start.api;

import java.time.Instant;

public record UpdateSportEventRequest(String name, String city, Instant startTime, Instant endTime) {
}
