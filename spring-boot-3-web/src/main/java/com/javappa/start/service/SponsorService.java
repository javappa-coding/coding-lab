package com.javappa.start.service;

import com.javappa.start.api.NewSponsorRequest;
import com.javappa.start.domain.PromoCode;
import com.javappa.start.domain.Sponsor;
import com.javappa.start.repository.SponsorRepository;
import com.javappa.start.support.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SponsorService {

    private final SponsorRepository sponsorRepository;
    //private final PromoCode promoCode;
    private final ApplicationContext applicationContext;

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

    public String generatePromoCode(Long id) {
        log.info("Promo code from sponsor id: " + id);
        PromoCode promoCode = applicationContext.getBean(PromoCode.class);
        String code = promoCode.getCode();
        log.info(code);
        log.info(promoCode.toString());
        //TODO: Save in the database
        return code;
    }
}