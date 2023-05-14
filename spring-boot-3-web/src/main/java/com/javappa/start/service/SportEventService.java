package com.javappa.start.service;

import com.javappa.start.api.*;
import com.javappa.start.domain.SportEvent;
import com.javappa.start.domain.Ticket;
import com.javappa.start.repository.SportEventRepository;
import com.javappa.start.support.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;
    private final TicketService ticketService;

    public List<SportEventResponse> getAll() {
        return sportEventRepository.findAll().stream()
                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
                        sportEvent.getName(), sportEvent.getCity(),
                        sportEvent.getStartTime(), sportEvent.getEndTime(), null)) // we don't want to retrieve all tickets when retrieving all events
                .collect(Collectors.toList());
    }

    public Long create(NewSportEventRequest eventRequest) {
        SportEvent event = new SportEvent( eventRequest.name(), eventRequest.city(),
                eventRequest.startTime(), eventRequest.endTime());
        SportEvent savedEvent = sportEventRepository.save(event);
        log.info("Event created");

        return savedEvent.getId();
    }

    public SportEventResponse getEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));
        return new SportEventResponse(id, event.getName(), event.getCity(),
                                                event.getStartTime(), event.getEndTime(),
                                                    event.getTickets().stream()
                                                    .map(ticket -> new TicketDTO(ticket.getSeatNumber(),
                                                                                   ticket.getPrice()))
                                                    .collect(Collectors.toList()));
    }

    public SportEventResponse updateEvent(Long id, UpdateSportEventRequest eventRequest) {
        SportEvent sportEvent = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEvent.setName(eventRequest.name());
        sportEvent.setCity(eventRequest.city());
        sportEvent.setStartTime(eventRequest.startTime());
        sportEvent.setEndTime(eventRequest.endTime());
        sportEventRepository.save(sportEvent);
        log.info("Event updated");

        return new SportEventResponse(id, sportEvent.getName(), sportEvent.getCity(),
                                            sportEvent.getStartTime(), sportEvent.getEndTime(), null); // we don't change tickets using update method, it is better to create a separated method
    }

    public void deleteEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEventRepository.delete(event);
        log.info("Event removed");
    }

    @Transactional
    public List<Long> addTicketsToEvent(Long eventId, NewTicketsRequest ticketsRequest) {
        SportEvent event = sportEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + eventId + " not found"));

        List<Ticket> tickets = ticketService.create(ticketsRequest, event);
        event.getTickets().addAll(tickets);

        log.info("Tickets added to the event with id: " + eventId);

        return tickets.stream().map(Ticket::getId).collect(Collectors.toList());
    }

}