package com.javappa.start.domain.sponsor.api;

import com.javappa.start.domain.shared.model.Sponsor;
import com.javappa.start.domain.sponsor.api.dto.NewSponsorRequest;
import com.javappa.start.domain.sponsor.api.dto.SponsorResponse;
import com.javappa.start.domain.sponsor.application.service.SponsorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
@Tag(name = "Sponsors")
public class SponsorApi {

    private final SponsorService sponsorService;

    @GetMapping("/{id}")
    @Operation(summary = "Get sponsor by id")
    public SponsorResponse getSponsor(@PathVariable Long id) {
        Sponsor sponsor = sponsorService.getSponsor(id);
        return new SponsorResponse(sponsor.getId(), sponsor.getName(), sponsor.getIndustry());
    }

    @PostMapping
    @Operation(summary = "Create new sponsor")
    public ResponseEntity<Void> createSponsor(@RequestBody NewSponsorRequest sponsorRequest) {
        Long id = sponsorService.create(sponsorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/promo-code/{id}")
    @Operation(summary = "Get promo code")
    public ResponseEntity<String> generatePromoCode(@PathVariable Long id) {
        String promoCode = sponsorService.generatePromoCode(id);
        return ResponseEntity.ok(promoCode);
    }

    @GetMapping("/promo-codes")
    @Operation(summary = "Generate promo codes")
    public ResponseEntity<Void> generatePromoCodes() {
        sponsorService.generateAndProcessPromoCodes();
        return ResponseEntity.ok().build();
    }

    // other methods when needed...
    //
    //    @PutMapping("/{id}")
    //    @Operation(summary = "Update sponsor")
    //    public Sponsor updateSponsor(@PathVariable Long id, @RequestBody Sponsor sponsor) {
    //        return sponsorService.updateSponsor(id, sponsor);
    //    }
    //
    //    @DeleteMapping("/{id}")
    //    @Operation(summary = "Delete sponsor")
    //    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
    //        sponsorService.deleteSponsor(id);
    //        return ResponseEntity.noContent().build();
    //    }
}