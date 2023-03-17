package com.example.api;

import com.example.api.NumberController;
import com.example.api.Models.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(NumberController.class);

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handleException(ResponseStatusException ex) {
        var errorModel = new ErrorDetails();

        errorModel.setErrorMessage(ex.getReason());

        logger.info("Exception: " + ex.getMessage());

        return new ResponseEntity<ErrorDetails>(errorModel, ex.getStatusCode());
    }
}