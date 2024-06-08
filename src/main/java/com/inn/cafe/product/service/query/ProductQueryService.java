package com.inn.cafe.product.service.query;

import java.util.List;

import com.inn.cafe.product.dto.query.ProductCategoryFilterQuery;
import com.inn.cafe.product.dto.query.ProductResponse;

public interface ProductQueryService {
  // Queries
  public List<ProductResponse> getAll();
  public List<ProductResponse> getAllByCategory(ProductCategoryFilterQuery filter);
  public List<ProductResponse> getAllByCategoryId(Integer categoryId);
  public ProductResponse getById(Integer id);
  public ProductResponse getByName(String name);

}
