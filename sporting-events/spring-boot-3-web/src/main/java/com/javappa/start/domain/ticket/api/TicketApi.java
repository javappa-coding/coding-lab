package com.javappa.start.domain.ticket.api;

import com.javappa.start.domain.ticket.api.dto.TicketCartResponse;
import com.javappa.start.domain.ticket.application.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Tickets")
public class TicketApi {

    private final TicketService ticketService;

    @PostMapping("/{ticketId}/cart")
    public ResponseEntity<Void> addTicketToCart(@PathVariable Long ticketId) {
        ticketService.addTicketToCart(ticketId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/tickets/cart")
    public TicketCartResponse getTicketsInCart() {
        return ticketService.getTicketsInCart();
    }
}