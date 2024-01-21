package com.javappa.start.domain.ticket.application.service;

import com.javappa.start.core.support.ResourceNotFoundException;
import com.javappa.start.domain.shared.model.SportEvent;
import com.javappa.start.domain.shared.model.Ticket;
import com.javappa.start.domain.ticket.api.dto.TicketCartResponse;
import com.javappa.start.domain.ticket.application.TicketCart;
import com.javappa.start.domain.ticket.application.dto.NewTicketsCommand;
import com.javappa.start.domain.ticket.application.dto.TicketCartDTO;
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
    private final TicketCart ticketCart;

    public List<Ticket> create(NewTicketsCommand newTicketsCommand, SportEvent event) {
        List<Ticket> tickets = newTicketsCommand.tickets().stream()
                .map(ticketDTO -> new Ticket(ticketDTO.seatNumber(), ticketDTO.price(), event))
                .collect(Collectors.toList());

        return ticketRepository.saveAll(tickets);
    }

    public void addTicketToCart(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket " + ticketId + " not found"));
        TicketCartDTO ticketDto = new TicketCartDTO(ticket.getId(), ticket.getSeatNumber(), ticket.getPrice());
        ticketCart.addTicket(ticketDto);
    }

    public TicketCartResponse getTicketsInCart() {
        return new TicketCartResponse(ticketCart.getTickets());
    }
}