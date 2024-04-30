package com.simplilearn.mihneapopa.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RequestsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFoundException(){
        return new ResponseEntity<>("User not found!", HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<String> handleProductNotFoundException(){
        return new ResponseEntity<>("Product not found!", HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<String> handleOrderNotFoundException(){
        return new ResponseEntity<>("Order not found!", HttpStatusCode.valueOf(404));
    }
}
