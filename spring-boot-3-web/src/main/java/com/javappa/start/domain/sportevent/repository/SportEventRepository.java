package com.javappa.start.domain.sportevent.repository;

import com.javappa.start.domain.sportevent.domain.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}