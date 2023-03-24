package com.javappa.start.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleErrors(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError()
                                .body("Something went wrong. Please try again later.");
    }
}
