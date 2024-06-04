package com.inn.cafe.user.exception;

import com.inn.cafe.exception.CustomNotFoundException;

public class UserNotFoundException extends CustomNotFoundException {
  public UserNotFoundException(String message) {
      super(message);
  }
}