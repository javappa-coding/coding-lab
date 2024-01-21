package com.javappa.start.domain.ticket.application.dto;

import java.math.BigDecimal;

public record TicketCartDTO(Long id, String seatNumber, BigDecimal price) {

}
