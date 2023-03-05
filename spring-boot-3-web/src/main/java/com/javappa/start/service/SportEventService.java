package com.javappa.start.service;

import com.javappa.start.api.SportEventResponse;
import com.javappa.start.domain.SportEvent;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SportEventService {

    private AtomicLong index = new AtomicLong(3);
    private final Map<Long, SportEvent> events = new HashMap<>();

    {
        events.put(1L, new SportEvent(1L, "Volleyball match"));
        events.put(2L, new SportEvent(2L, "Football match"));
        events.put(3L, new SportEvent(3L, "Boxing fight"));
    }

    public List<SportEventResponse> getAll() {
        return events.values().stream()
                .map(sportEvent -> new SportEventResponse(sportEvent.getId(),
                        sportEvent.getName()))
                .toList();
    }
}
