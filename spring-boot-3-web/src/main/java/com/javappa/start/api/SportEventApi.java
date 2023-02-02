package com.javappa.start.api;

import com.javappa.start.domain.SportEvent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sport-events")
@Tag(name = "Sporting events")
public class SportEventApi {

    private final Map<Long, SportEvent> events = new HashMap<>();

    {
        events.put(1L, new SportEvent(1L, "Volleyball match"));
        events.put(2L, new SportEvent(2L, "Football match"));
        events.put(3L, new SportEvent(3L, "Boxing fight"));
    }

    @GetMapping
    @Operation(summary = "Gets all sporting events")
    List<SportEventResponse> getAll() {
        return events.values().stream()
                                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
                                                                            sportEvent.getName()))
                                .toList();
    }
}
