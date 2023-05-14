package com.javappa.start.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sport_events")
public class SportEvent {

    @Id
    @SequenceGenerator(name = "SportingEventsSequence", sequenceName = "sporting_events_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SportingEventsSequence")
    private Long id;
    private String name;
    private String city;
    private Instant startTime;
    private Instant endTime;

    public SportEvent(String name, String city, Instant startTime, Instant endTime) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
}
