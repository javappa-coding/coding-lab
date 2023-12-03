package com.javappa.start.domain.sportevent.application.service;

import com.javappa.start.core.support.ResourceNotFoundException;
import com.javappa.start.domain.ticket.application.dto.NewTicketsCommand;
import com.javappa.start.domain.ticket.application.dto.TicketDTO;
import com.javappa.start.domain.shared.model.Sponsor;
import com.javappa.start.domain.shared.model.SportEvent;
import com.javappa.start.domain.shared.model.Ticket;
import com.javappa.start.domain.sponsor.application.service.SponsorService;
import com.javappa.start.domain.sportevent.api.dto.AddTicketToSportEventRequest;
import com.javappa.start.domain.sportevent.api.dto.NewSportEventRequest;
import com.javappa.start.domain.sportevent.api.dto.SportEventResponse;
import com.javappa.start.domain.sportevent.api.dto.UpdateSportEventRequest;
import com.javappa.start.domain.sportevent.domain.SportEventSponsorDTO;
import com.javappa.start.domain.sportevent.domain.SportEventTicketDTO;
import com.javappa.start.domain.sportevent.domain.repository.SportEventRepository;
import com.javappa.start.domain.ticket.application.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
@Service
public class SportEventService {

    private final SportEventRepository sportEventRepository;
    private final TicketService ticketService;
    private final SponsorService sponsorService;

    public List<SportEventResponse> getAll() {
        return sportEventRepository.findAll().stream().map(
                        sportEvent -> new SportEventResponse(sportEvent.getId(), sportEvent.getName(),
                                sportEvent.getCity(),
                                sportEvent.getStartTime(), sportEvent.getEndTime(), null,
                                null))// we don't want to retrieve all tickets and sponsors when retrieving all events)
                .collect(toList());
    }

    public Long create(NewSportEventRequest eventRequest) {
        SportEvent event = new SportEvent(eventRequest.name(), eventRequest.city(), eventRequest.startTime(),
                eventRequest.endTime());
        SportEvent savedEvent = sportEventRepository.save(event);
        log.info("Event created");

        return savedEvent.getId();
    }

    public SportEventResponse getEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + id + " not found"));
        return new SportEventResponse(id, event.getName(), event.getCity(), event.getStartTime(), event.getEndTime(),
                event.getTickets().stream().map(ticket -> new SportEventTicketDTO(ticket.getSeatNumber(), ticket.getPrice()))
                        .collect(toList()), event.getSponsors().stream()
                .map(sponsor -> new SportEventSponsorDTO(sponsor.getId(), sponsor.getName(), sponsor.getIndustry()))
                .collect(Collectors.toSet()));
    }

    public SportEventResponse updateEvent(Long id, UpdateSportEventRequest eventRequest) {
        SportEvent sportEvent = sportEventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEvent.setName(eventRequest.name());
        sportEvent.setCity(eventRequest.city());
        sportEvent.setStartTime(eventRequest.startTime());
        sportEvent.setEndTime(eventRequest.endTime());
        sportEventRepository.save(sportEvent);
        log.info("Event updated");

        return new SportEventResponse(id, sportEvent.getName(), sportEvent.getCity(), sportEvent.getStartTime(),
                sportEvent.getEndTime(), null,
                null); // we don't change tickets using update method, it is better to create a separated method
    }

    public void deleteEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEventRepository.delete(event);
        log.info("Event removed");
    }

    @Transactional
    public List<Long> addTicketsToEvent(Long eventId, AddTicketToSportEventRequest addTicketRequest) {
        SportEvent event = sportEventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + eventId + " not found"));

        List<TicketDTO> ticketDTOs = addTicketRequest.tickets().stream().map(
                        sportEventTicketDTO -> new TicketDTO(sportEventTicketDTO.seatNumber(),
                                sportEventTicketDTO.price()))
                .collect(toList());

        List<Ticket> tickets = ticketService.create(new NewTicketsCommand(ticketDTOs), event);
        event.getTickets().addAll(tickets);

        log.info("Tickets added to the event with id: " + eventId);

        return tickets.stream().map(Ticket::getId).collect(toList());
    }

    @Transactional
    public void addSponsorToEvent(Long eventId, Long sponsorId) {
        SportEvent event = sportEventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + eventId + " not found"));
        Sponsor sponsor = sponsorService.getSponsor(sponsorId);
        event.getSponsors().add(sponsor);
        sponsor.getEvents().add(event);
    }

    @Transactional
    public void removeSponsorFromEvent(Long eventId, Long sponsorId) {
        SportEvent event = sportEventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("SportEvent " + eventId + " not found"));
        Sponsor sponsor = sponsorService.getSponsor(sponsorId);
        event.getSponsors().remove(sponsor);
        sponsor.getEvents().remove(event);
    }

}