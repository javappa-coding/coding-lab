package com.javappa.start.api;

import java.math.BigDecimal;

public record TicketDTO(String seatNumber, BigDecimal price) {
}