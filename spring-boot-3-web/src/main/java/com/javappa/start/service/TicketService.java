package com.javappa.start.service;

import com.javappa.start.api.*;
import com.javappa.start.domain.SportEvent;
import com.javappa.start.domain.Ticket;
import com.javappa.start.repository.TicketRepository;
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