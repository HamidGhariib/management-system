package com.management.system.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionController {

    @ExceptionHandler(value = OrderFailedException.class)
    public ResponseEntity<Object> exception(OrderFailedException exception) {
        return new ResponseEntity<>("Error in product availability", HttpStatus.BAD_REQUEST);
    }
}
