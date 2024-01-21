package com.javappa.start.domain.sportevent.api.dto;

import com.javappa.start.domain.sportevent.domain.SportEventSponsorDTO;
import com.javappa.start.domain.sportevent.domain.SportEventTicketDTO;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record SportEventResponse(Long id, String name, String city,
                                    Instant startTime, Instant endTime, List<SportEventTicketDTO> ticketDTO,
                                    Set<SportEventSponsorDTO> sponsorDTO) {
}
