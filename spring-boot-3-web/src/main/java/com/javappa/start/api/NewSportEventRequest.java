package com.javappa.start.api;

import java.time.Instant;

public record NewSportEventRequest(String name, String city, Instant startTime, Instant endTime) {
}
