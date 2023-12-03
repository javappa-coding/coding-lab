package com.javappa.start.domain.ticket.application.service;

import com.javappa.start.domain.ticket.application.dto.NewTicketsCommand;
import com.javappa.start.domain.shared.model.SportEvent;
import com.javappa.start.domain.shared.model.Ticket;
import com.javappa.start.domain.ticket.domain.repository.TicketRepository;
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

    public List<Ticket> create(NewTicketsCommand newTicketsCommand, SportEvent event) {
        List<Ticket> tickets = newTicketsCommand.tickets().stream()
                .map(ticketDTO -> new Ticket(ticketDTO.seatNumber(), ticketDTO.price(), event))
                .collect(Collectors.toList());

        return ticketRepository.saveAll(tickets);
    }
}