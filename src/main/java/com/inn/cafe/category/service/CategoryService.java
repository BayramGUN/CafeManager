package com.inn.cafe.category.service;

import java.util.List;

import com.inn.cafe.category.dto.command.CreateCategoryRequest;
import com.inn.cafe.category.dto.command.UpdateCategoryRequest;
import com.inn.cafe.category.dto.query.CategoryResponse;

public interface CategoryService {
  // Queries
  public List<CategoryResponse> getAll();
  public CategoryResponse getById(Integer id);
  public CategoryResponse getByName(String name);

  // Commands
  public String delete(Integer id);
  public String insert(CreateCategoryRequest request);
  public String update(Integer id, UpdateCategoryRequest request);
}
