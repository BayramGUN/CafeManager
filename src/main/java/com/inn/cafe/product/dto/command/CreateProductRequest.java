package com.inn.cafe.product.dto.command;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
  private String name;
  private String description;
  private BigDecimal price;
  private Integer categoryId;
}
