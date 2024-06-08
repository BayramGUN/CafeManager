package com.inn.cafe.category.service.command;

import org.springframework.stereotype.Service;

import com.inn.cafe.category.dao.CategoryRepository;
import com.inn.cafe.category.data.Category;
import com.inn.cafe.category.dto.command.CreateCategoryRequest;
import com.inn.cafe.category.dto.command.UpdateCategoryRequest;
import com.inn.cafe.category.dto.query.CategoryResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {
  private final CategoryRepository repository;

  @Override
  public String delete(Integer id) {
    try {      
      repository.delete(repository.findById(id).orElseThrow(
        () -> new RuntimeException("Category not found!")
      ));
      return "Category Deleted Successfully!";
    } catch (Exception e) {
      return "Category can not deleted!";
    }
  }

  @Override
  public CategoryResponse insert(CreateCategoryRequest request) {
    var entity = ObjectMapperUtils.map(request, Category.class);
    var category = repository.save(entity);
    return ObjectMapperUtils.map(category, CategoryResponse.class);
  }

  @Override
  public String update(Integer id, UpdateCategoryRequest request) {
    var categoryToUpdate = repository.findById(id).orElseThrow();
    categoryToUpdate.setName(request.getName());
    repository.save(categoryToUpdate);
    return "Category Updated Successfully!";
  }
  
}
