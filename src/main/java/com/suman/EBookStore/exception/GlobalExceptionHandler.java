package com.suman.EBookStore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationException(MethodArgumentNotValidException e){
    Map<String,String> error= new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(x-> error.put(x.getField(), x.getDefaultMessage()));
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}
}
