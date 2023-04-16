package com.javappa.start.service;


import com.javappa.start.api.NewSportEventRequest;
import com.javappa.start.api.SportEventResponse;
import com.javappa.start.api.UpdateSportEventRequest;
import com.javappa.start.domain.SportEvent;
import com.javappa.start.repository.SportEventRepository;
import com.javappa.start.support.ResourceNotFoundException;
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

    public List<SportEventResponse> getAll() {
        return sportEventRepository.findAll().stream()
                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
                        sportEvent.getName()))
                .collect(Collectors.toList());
    }

    public Long create(NewSportEventRequest eventRequest) {
        SportEvent event = new SportEvent(null, eventRequest.name());
        SportEvent savedEvent = sportEventRepository.save(event);
        log.info("Event created");

        return savedEvent.getId();
    }

    public SportEventResponse getEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));
        return new SportEventResponse(id, event.getName());
    }

    public SportEventResponse updateEvent(Long id, UpdateSportEventRequest eventRequest) {
        SportEvent sportEvent = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEvent.setName(eventRequest.name());
        sportEventRepository.save(sportEvent);
        log.info("Event updated");

        return new SportEventResponse(id, sportEvent.getName());
    }

    public void deleteEvent(Long id) {
        SportEvent event = sportEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SportEvent " + id + " not found"));

        sportEventRepository.delete(event);
        log.info("Event removed");
    }
}