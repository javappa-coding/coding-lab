package com.javappa.start.service;

import com.javappa.start.api.NewSponsorRequest;
import com.javappa.start.domain.Sponsor;
import com.javappa.start.repository.SponsorRepository;
import com.javappa.start.support.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SponsorService {

    private final SponsorRepository sponsorRepository;

    public Long create(NewSponsorRequest sponsorRequest) {
        Sponsor sponsor = new Sponsor(sponsorRequest.name(), sponsorRequest.industry());
        Sponsor savedSponsor = sponsorRepository.save(sponsor);
        log.info("Sponsor created");

        return savedSponsor.getId();
    }

    public Sponsor getSponsor(Long id) {
        return sponsorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sponsor " + id + " not found"));
    }

    // other methods when needed...
}