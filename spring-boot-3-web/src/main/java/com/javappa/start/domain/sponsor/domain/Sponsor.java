package com.javappa.start.domain.sponsor.domain;

import com.javappa.start.domain.sportevent.domain.SportEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sponsors")
public class Sponsor {

    @Id
    @SequenceGenerator(name = "SponsorSequence",
                       sequenceName = "sponsor_seq",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "SponsorSequence")
    private Long id;
    private String name;
    private String industry;

    public Sponsor(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    @ManyToMany(mappedBy = "sponsors")
    private Set<SportEvent> events = new HashSet<>();
}