package com.javappa.start.domain.sportevent.api;

import java.time.Instant;

public record UpdateSportEventRequest(String name, String city, Instant startTime, Instant endTime) {
}
