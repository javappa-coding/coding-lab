package com.javappa.start.api;

import com.javappa.start.domain.SportEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/api/sport-events")
@Tag(name = "Sporting events")
public class SportEventApi {

    private AtomicLong index = new AtomicLong(3);

    private final Map<Long, SportEvent> events = new HashMap<>();

    {
        events.put(1L, new SportEvent(1L, "Volleyball match"));
        events.put(2L, new SportEvent(2L, "Football match"));
        events.put(3L, new SportEvent(3L, "Boxing fight"));
    }

    @GetMapping
    @Operation(summary = "Get all data")
    List<SportEventResponse> getAll() {
        return events.values().stream()
                                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
                                                                            sportEvent.getName()))
                                .toList();
    }

    @PostMapping
    @Operation(summary = "Create new data")
    void create(@RequestBody NewSportEventRequest eventRequest) {
        SportEvent event = new SportEvent(index.incrementAndGet(), eventRequest.name());
        events.put(index.get(), event);

        log.info("Event created");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get data by id")
    SportEventResponse getEvent(@PathVariable Long id) {
        Optional<SportEvent> item = Optional.ofNullable(events.get(id));
        return new SportEventResponse(id, item.orElseThrow().getName());
    }
}
