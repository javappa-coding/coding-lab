package com.javappa.start.core.support;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

//    Simple solution without additional information:
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException exception) {
//        log.error("Exception caught: {}", exception.getMessage());
//        return ResponseEntity.notFound().build();
//    }

//  Advanced solution with additional information
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error("Exception caught: {}", exception.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Resource not found because it is archived");
        problemDetail.setProperty("message", "Couldn't find resource to be deleted");
        problemDetail.setType(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
        return problemDetail;
    }
}
