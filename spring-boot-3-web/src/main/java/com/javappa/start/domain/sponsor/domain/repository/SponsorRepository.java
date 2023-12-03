package com.javappa.start.domain.sponsor.domain.repository;

import com.javappa.start.domain.shared.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}