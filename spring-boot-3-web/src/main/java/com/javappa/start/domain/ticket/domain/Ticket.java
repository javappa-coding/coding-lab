package com.javappa.start.domain.ticket.domain;

import com.javappa.start.domain.sportevent.domain.SportEvent;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @SequenceGenerator(name = "TicketSequence", sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TicketSequence")
    private Long id;
    private String seatNumber;
    private BigDecimal price;

    public Ticket(String seatNumber, BigDecimal price, SportEvent sportEvent) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.sportEvent = sportEvent;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private SportEvent sportEvent;
}