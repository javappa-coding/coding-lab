package com.javappa.start.core.infrastructure;

import com.javappa.start.core.security.domain.User;
import com.javappa.start.core.security.repository.UserRepository;
import com.javappa.start.domain.sportevent.api.dto.AddTicketToSportEventRequest;
import com.javappa.start.domain.sportevent.api.dto.NewSportEventRequest;
import com.javappa.start.domain.sportevent.application.service.SportEventService;
import com.javappa.start.domain.sportevent.domain.SportEventTicketDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SportEventService sportEventService;

    @PostConstruct
    private void initializeDatabase() {
        Optional<User> existingUser = userRepository.findByUsername("doej");
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("doej");
            newUser.setPassword(passwordEncoder.encode("aaaa"));
            userRepository.save(newUser);
        }
        Optional<User> existingUser2 = userRepository.findByUsername("doej2");
        if (existingUser2.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("doej2");
            newUser.setPassword(passwordEncoder.encode("aaaa"));
            userRepository.save(newUser);
        }
    }

    @PostConstruct
    private void initializeEventsAndTickets() {
        sampleEvents().forEach(sportEventService::create);
        AddTicketToSportEventRequest soccerEventTickets = ticketsForSoccerEvent();
        sportEventService.addTicketsToEvent(1L, soccerEventTickets);
    }

    private AddTicketToSportEventRequest ticketsForSoccerEvent() {
        SportEventTicketDTO ticketSoccer1 = new SportEventTicketDTO("A1", new BigDecimal("50.00"));
        SportEventTicketDTO ticketSoccer2 = new SportEventTicketDTO("A2", new BigDecimal("55.00"));
        SportEventTicketDTO ticketSoccer3 = new SportEventTicketDTO("B1", new BigDecimal("60.00"));
        List<SportEventTicketDTO> ticketsSoccer = Arrays.asList(ticketSoccer1, ticketSoccer2, ticketSoccer3);
        return new AddTicketToSportEventRequest(ticketsSoccer);
    }

    public List<NewSportEventRequest> sampleEvents() {
        NewSportEventRequest soccerMatch = new NewSportEventRequest(
                "Soccer Match",
                "Warsaw",
                Instant.parse("2023-07-15T18:00:00Z"),
                Instant.parse("2023-07-15T20:00:00Z")
        );
        NewSportEventRequest basketballGame = new NewSportEventRequest(
                "National Basketball Championship",
                "New York",
                Instant.parse("2023-08-10T15:30:00Z"),
                Instant.parse("2023-08-10T17:30:00Z")
        );

        NewSportEventRequest tennisTournament = new NewSportEventRequest(
                "Grand Slam Tennis Tournament",
                "London",
                Instant.parse("2023-06-20T09:00:00Z"),
                Instant.parse("2023-06-20T18:00:00Z")
        );
        return Arrays.asList(soccerMatch, basketballGame, tennisTournament);
    }
}