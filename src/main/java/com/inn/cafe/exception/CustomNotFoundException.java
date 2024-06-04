package com.inn.cafe.exception;

public class CustomNotFoundException extends RuntimeException {
  public CustomNotFoundException(String message) {
    super(message);
  }
}
