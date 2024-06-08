package com.inn.cafe.product.service.query;

import java.util.List;

import com.inn.cafe.product.dto.query.ProductResponse;

public interface ProductQueryService {
  // Queries
  public List<ProductResponse> getAll();
  public ProductResponse getById(Integer id);
  public ProductResponse getByName(String name);

}
