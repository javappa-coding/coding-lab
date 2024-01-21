package com.javappa.start.domain.sportevent.domain.repository;

import com.javappa.start.domain.shared.model.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}