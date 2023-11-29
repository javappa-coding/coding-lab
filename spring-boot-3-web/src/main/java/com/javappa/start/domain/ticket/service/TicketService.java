package com.javappa.start.domain.ticket.service;

import com.javappa.start.domain.sportevent.domain.SportEvent;
import com.javappa.start.domain.ticket.repository.TicketRepository;
import com.javappa.start.domain.ticket.api.NewTicketsRequest;
import com.javappa.start.domain.ticket.domain.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> create(NewTicketsRequest ticketsRequest, SportEvent event) {
        List<Ticket> tickets = ticketsRequest.tickets().stream()
                .map(ticketDTO -> new Ticket(ticketDTO.seatNumber(), ticketDTO.price(), event))
                .collect(Collectors.toList());

        return ticketRepository.saveAll(tickets);
    }
}