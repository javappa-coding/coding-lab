package com.javappa.start.domain.ticket.application.dto;

import java.util.List;

public record NewTicketsCommand(List<TicketDTO> tickets) {

}
