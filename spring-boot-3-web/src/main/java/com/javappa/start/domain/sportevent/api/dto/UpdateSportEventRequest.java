package com.javappa.start.domain.sportevent.api.dto;

import java.time.Instant;

public record UpdateSportEventRequest(String name, String city, Instant startTime, Instant endTime) {
}
