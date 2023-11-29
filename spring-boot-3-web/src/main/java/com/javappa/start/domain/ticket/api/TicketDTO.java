package com.javappa.start.domain.ticket.api;

import java.math.BigDecimal;

public record TicketDTO(String seatNumber, BigDecimal price) {
}