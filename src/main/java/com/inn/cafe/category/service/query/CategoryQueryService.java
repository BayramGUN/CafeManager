package com.inn.cafe.category.service.query;

import java.util.List;

import com.inn.cafe.category.dto.query.CategoryResponse;

public interface CategoryQueryService {
  // Queries
  public List<CategoryResponse> getAll();
  public CategoryResponse getById(Integer id);
  public CategoryResponse getByName(String name);

}
