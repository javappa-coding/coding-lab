package com.javappa.start.domain;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
@Getter
@Scope(value = "prototype")
public class PromoCode {

    private final String code;
    private final LocalDateTime generatedAt;

    public PromoCode() {
        this.generatedAt = LocalDateTime.now();
        this.code = generateUniqueCode();
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8) + "-"
                                    + generatedAt.toEpochSecond(ZoneOffset.UTC);
    }
}