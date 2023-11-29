package com.javappa.start.domain.sportevent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Deprecated
public class SportEventServiceOld {

//    For historical reasons still here
//    private AtomicLong index = new AtomicLong(3);
//    private final Map<Long, SportEvent> events = new HashMap<>();
//
//    {
//        events.put(1L, new SportEvent(1L, "Volleyball match", ""));
//        events.put(2L, new SportEvent(2L, "Football match", ""));
//        events.put(3L, new SportEvent(3L, "Boxing fight", ""));
//    }
//
//    public List<SportEventResponse> getAll() {
//        return events.values().stream()
//                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
//                        sportEvent.getName()))
//                .toList();
//    }
//
//    public Long create(NewSportEventRequest eventRequest) {
//        SportEvent event = new SportEvent(index.incrementAndGet(), eventRequest.name(), eventRequest.city());
//        events.put(index.get(), event);
//        log.info("Event created");
//
//        return event.getId();
//    }
//
//    public SportEventResponse getEvent(Long id) {
//        Optional<SportEvent> event = Optional.ofNullable(events.get(id));
//        return new SportEventResponse(id, event.orElseThrow().getName());
//    }
//
//    public SportEventResponse updateEvent(Long id, UpdateSportEventRequest eventRequest) {
//
//        Optional<SportEvent> event = Optional.ofNullable(events.get(id));
//        SportEvent sportEvent = event.orElseThrow();
//        sportEvent.setName(eventRequest.name());
//        log.info("Event updated");
//
//        return new SportEventResponse(id, sportEvent.getName());
//    }
//
//    public void deleteEvent(Long id) {
//        SportEvent event = Optional.ofNullable(events.get(id)).orElseThrow(() ->
//                new ResourceNotFoundException("SportEvent " + id + " not found"));
//        events.remove(event.getId());
//        log.info("Event removed");
//    }

}
