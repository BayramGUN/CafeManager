package com.inn.cafe.product.service.command;

import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;

public interface ProductCommandService {
  // Commands
  public String delete(Integer id);
  public String insert(CreateProductRequest request);
  public String update(Integer id, UpdateProductRequest request);
}
