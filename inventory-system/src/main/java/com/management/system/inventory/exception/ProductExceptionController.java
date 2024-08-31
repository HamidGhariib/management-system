package com.management.system.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductOutOfRangeException.class)
    public ResponseEntity<Object> exception(ProductOutOfRangeException exception) {
        return new ResponseEntity<>("Product out of range", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductNotAvailableException.class)
    public ResponseEntity<Object> exception(ProductNotAvailableException exception) {
        return new ResponseEntity<>("Product is not available", HttpStatus.BAD_REQUEST);
    }
}
