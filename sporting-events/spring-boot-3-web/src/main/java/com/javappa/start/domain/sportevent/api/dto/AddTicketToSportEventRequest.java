package com.javappa.start.domain.sportevent.api.dto;

import com.javappa.start.domain.sportevent.domain.SportEventTicketDTO;

import java.util.List;

public record AddTicketToSportEventRequest(List<SportEventTicketDTO> tickets) {


}
