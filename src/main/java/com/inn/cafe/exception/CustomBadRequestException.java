package com.inn.cafe.exception;

public class CustomBadRequestException extends RuntimeException {
  public CustomBadRequestException(String message) {
    super(message);
  }
}
