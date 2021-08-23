package com.spring.school.common;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionAdvisor {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleModelValidationException(
      MethodArgumentNotValidException ex) {

    List<String> errors = new ArrayList<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }

    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, errors);

    return ResponseEntity.badRequest().body(apiErrorResponse);
  }

  // 400
  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleBadRequestException(RuntimeException ex) {

    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST,
        ex.getMessage());

    return ResponseEntity.badRequest().body(apiErrorResponse);
  }

  // 401
  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity handleAccessDeniedException(
      final AccessDeniedException ex) {

    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED,
        ex.getMessage());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorResponse);
  }

  // 500
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception ex) {
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        ex.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
  }

}
