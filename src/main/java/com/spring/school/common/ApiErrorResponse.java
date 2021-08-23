package com.spring.school.common;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorResponse {

  private HttpStatus status;
  private List<String> errors;


  public ApiErrorResponse(List<String> errors) {
    super();
    this.errors = errors;
  }

  public ApiErrorResponse(String error) {
    super();
    errors = Arrays.asList(error);
  }

  public ApiErrorResponse(HttpStatus status, String error) {
    super();
    this.status = status;
    errors = Arrays.asList(error);
  }

  public ApiErrorResponse(HttpStatus status, List<String> errors) {
    super();
    this.status = status;
    this.errors = errors;
  }
}
