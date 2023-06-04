package com.javappa.start.api;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record SportEventResponse(Long id, String name, String city,
                                    Instant startTime, Instant endTime, List<TicketDTO> ticketDTO,
                                    Set<SponsorDTO> sponsorDTO) {
}
