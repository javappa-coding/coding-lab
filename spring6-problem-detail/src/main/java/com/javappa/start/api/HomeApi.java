package com.javappa.start.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi {

    @GetMapping("/welcome")
    String hello() {

        if(true) {
            throw new RuntimeException("OMG Something went wrong");
        }

        return "I'm Spring Boot 3 API";
    }
}
