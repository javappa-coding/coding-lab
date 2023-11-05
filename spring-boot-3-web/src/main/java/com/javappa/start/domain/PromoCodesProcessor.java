package com.javappa.start.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PromoCodesProcessor {

    private final List<String> codes = new ArrayList<>(
            Arrays.asList("PredefinedCode1",
                            "PredefinedCode2",
                            "PredefinedCode3"));

    public void processCodes(List<String> codes) {
        this.codes.addAll(codes);
        //some algorithms but we run printing only:
        this.codes.forEach(log::info);
    }
}
