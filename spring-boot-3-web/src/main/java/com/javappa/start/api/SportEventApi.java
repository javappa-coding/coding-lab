package com.javappa.start.api;

import com.javappa.start.service.SportEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sport-events")
@RequiredArgsConstructor
@Tag(name = "Sporting events")
public class SportEventApi {

    private final SportEventService sportEventService;

    @GetMapping
    @Operation(summary = "Get all events")
    List<SportEventResponse> getAll() {
        return sportEventService.getAll();
    }

    @PostMapping
    @Operation(summary = "Create new event")
    ResponseEntity<Void> create(@RequestBody NewSportEventRequest eventRequest) {
        Long id = sportEventService.create(eventRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by id")
    SportEventResponse getEvent(@PathVariable Long id) {
        return sportEventService.getEvent(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update event")
    SportEventResponse updateEvent(@PathVariable Long id, @RequestBody UpdateSportEventRequest eventRequest) {
        return sportEventService.updateEvent(id, eventRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete event")
    ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        sportEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/tickets")
    @Operation(summary = "Add tickets to an event")
    public ResponseEntity<List<Long>> addTicketsToEvent(@PathVariable Long eventId,
                                                        @RequestBody NewTicketsRequest ticketsRequest) {
        List<Long> ticketIds = sportEventService.addTicketsToEvent(eventId, ticketsRequest);
        return ResponseEntity.ok(ticketIds);
    }


//    We use CustomExceptionHandler class now
//    @ExceptionHandler(Exception.class)
//    ResponseEntity<String> handleException(Exception exception) {
//        log.error("Exception caught: {}", exception.getMessage());
//        return ResponseEntity.internalServerError()
//                                .body("Something went wrong. Please try again later.");
//    }

}
