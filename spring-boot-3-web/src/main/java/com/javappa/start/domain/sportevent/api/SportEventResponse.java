package com.javappa.start.domain.sportevent.api;

import com.javappa.start.domain.sponsor.api.SponsorDTO;
import com.javappa.start.domain.ticket.api.TicketDTO;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record SportEventResponse(Long id, String name, String city,
                                    Instant startTime, Instant endTime, List<TicketDTO> ticketDTO,
                                    Set<SponsorDTO> sponsorDTO) {
}
