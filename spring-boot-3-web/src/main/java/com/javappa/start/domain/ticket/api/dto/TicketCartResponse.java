package com.javappa.start.domain.ticket.api.dto;

import com.javappa.start.domain.ticket.application.dto.TicketCartDTO;

import java.util.Set;

public record TicketCartResponse(Set<TicketCartDTO> tickets) {
}
