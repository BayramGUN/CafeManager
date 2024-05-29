package com.inn.cafe.user.data.enums;

public enum Role {
  USER,
  ADMIN;
  @Override
  public String toString() {
    return name();
  }
}
