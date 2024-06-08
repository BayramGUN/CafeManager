package com.inn.cafe.product.data.enums;

public enum ProductStatus {
  ACTIVE,
  INACTIVE;
  @Override
  public String toString() {
    return name();
  }
}
