package com.javappa.start.domain.ticket.repository;

import com.javappa.start.domain.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}