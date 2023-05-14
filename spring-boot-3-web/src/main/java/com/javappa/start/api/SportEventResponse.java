package com.javappa.start.api;

import java.time.Instant;
import java.util.List;

public record SportEventResponse(Long id, String name, String city,
                                    Instant startTime, Instant endTime, List<TicketDTO> ticketDTO) {
}
