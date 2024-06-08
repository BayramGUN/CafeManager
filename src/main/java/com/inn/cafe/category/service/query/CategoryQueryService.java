package com.inn.cafe.category.service.query;

import java.util.List;

import com.inn.cafe.category.dto.query.CategoryResponse;
import com.inn.cafe.product.dto.query.ProductResponse;

public interface CategoryQueryService {
  // Queries
  public List<CategoryResponse> getAll();
  public List<ProductResponse> getCategoryProducts(Integer id);
  public CategoryResponse getById(Integer id);
  public CategoryResponse getByName(String name);

}
