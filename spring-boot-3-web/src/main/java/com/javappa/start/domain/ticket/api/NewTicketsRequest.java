package com.javappa.start.domain.ticket.api;

import java.util.List;

public record NewTicketsRequest(List<TicketDTO> tickets) {
}
