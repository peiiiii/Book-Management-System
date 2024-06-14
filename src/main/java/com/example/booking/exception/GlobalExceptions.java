package com.example.booking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptions.class);
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception){
        logger.warn("An illegal argument was passed: {}",exception.getMessage(),exception);
        return ResponseEntity.badRequest().body("An illegal argument was passed");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        logger.error("An error occurred: {}", exception.getMessage(), exception);
        return ResponseEntity.status(500).body("An internal server error occurred");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException exception) {
        logger.error("A null pointer exception occurred: {}", exception.getMessage(), exception);
        return ResponseEntity.status(500).body("A null pointer exception occurred");
    }

}
