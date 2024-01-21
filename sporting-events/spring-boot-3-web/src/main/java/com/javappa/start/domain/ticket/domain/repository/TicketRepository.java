package com.javappa.start.domain.ticket.domain.repository;

import com.javappa.start.domain.shared.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}