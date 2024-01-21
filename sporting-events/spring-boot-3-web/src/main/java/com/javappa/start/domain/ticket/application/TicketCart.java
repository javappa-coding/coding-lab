package com.javappa.start.domain.ticket.application;

import com.javappa.start.domain.ticket.application.dto.TicketCartDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class TicketCart {

    private final Set<TicketCartDTO> tickets = new HashSet<>();

    public void addTicket(TicketCartDTO ticket) {
        tickets.add(ticket);
    }

    public Set<TicketCartDTO> getTickets() {
        return tickets;
    }
}