package com.javappa.start.domain.sponsor.promocode;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
@Getter
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PromoCode {

    private final String code;
    private final LocalDateTime generatedAt;

    public PromoCode() {
        this.generatedAt = LocalDateTime.now();
        this.code = generateUniqueCode();

        System.identityHashCode(this);
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8) + "-"
                                    + generatedAt.toEpochSecond(ZoneOffset.UTC);
    }
}