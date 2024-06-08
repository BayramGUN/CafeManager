package com.inn.cafe.category.service.command;


import com.inn.cafe.category.dto.command.CreateCategoryRequest;
import com.inn.cafe.category.dto.command.UpdateCategoryRequest;
import com.inn.cafe.category.dto.query.CategoryResponse;

public interface CategoryCommandService {
  // Commands
  public String delete(Integer id);
  public CategoryResponse insert(CreateCategoryRequest request);
  public String update(Integer id, UpdateCategoryRequest request);
}
