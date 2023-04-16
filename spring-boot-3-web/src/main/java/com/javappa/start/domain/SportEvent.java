package com.javappa.start.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
