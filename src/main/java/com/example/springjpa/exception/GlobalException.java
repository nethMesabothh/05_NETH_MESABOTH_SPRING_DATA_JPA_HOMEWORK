package com.example.springjpa.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

  @ExceptionHandler(NotFoundException.class)
  public ProblemDetail handleNotFoundException(NotFoundException e) {

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

    problemDetail.setProperty("timestamp", LocalDateTime.now());

    return problemDetail;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidationException(MethodArgumentNotValidException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Validation Failed");

    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    problemDetail.setProperties(Map.of(
            "timestamp", LocalDateTime.now(),
            "errors", errors
    ));

    return problemDetail;
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {

    Map<String, String> errors = new HashMap<>();

    e.getParameterValidationResults().forEach(parameterValidationResult -> {
      String paramName = parameterValidationResult.getMethodParameter().getParameterName();
      for (var messageError : parameterValidationResult.getResolvableErrors()) {
        errors.put(paramName, messageError.getDefaultMessage());
      }
    });

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Method Parameter Validation Failed!");
    problemDetail.setProperties(Map.of(
            "timestamp", LocalDateTime.now(),
            "errors", errors
    ));


    return problemDetail;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Invalid Request");
    problemDetail.setDetail(e.getMessage());
    problemDetail.setProperty("timestamp", LocalDateTime.now());
    return problemDetail;
  }

}