package com.javappa.start.domain.sponsor.repository;

import com.javappa.start.domain.sponsor.domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}