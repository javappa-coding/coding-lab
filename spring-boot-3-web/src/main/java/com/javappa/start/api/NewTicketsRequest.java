package com.javappa.start.api;

import java.util.List;

public record NewTicketsRequest(List<TicketDTO> tickets) {
}
