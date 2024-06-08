package com.inn.cafe.product.service.command;

import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;
import com.inn.cafe.product.dto.query.ProductResponse;

public interface ProductCommandService {
  // Commands
  public String delete(Integer id);
  public ProductResponse insert(CreateProductRequest request);
  public String update(Integer id, UpdateProductRequest request);
}
