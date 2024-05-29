package com.inn.cafe.category.service;

import java.util.List;

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
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  @Override
  public List<CategoryResponse> getAll() {
    return ObjectMapperUtils.mapAll(repository.findAll(), CategoryResponse.class);
  }

  @Override
  public CategoryResponse getById(Integer id) {
    var category = repository.findById(id).orElseThrow(
      () -> new RuntimeException("category not Found!"));
    return ObjectMapperUtils.map(category, CategoryResponse.class);    
  }
    
  @Override
  public CategoryResponse getByName(String name) {
    Category category = repository.findByName("name").orElseThrow(      
      () -> new RuntimeException("category not Found!"));
    return ObjectMapperUtils.map(category, CategoryResponse.class);
  }

  @Override
  public String delete(Integer id) {
    try {      
      repository.delete(repository.findById(id).orElseThrow());
      return "Category Deleted Successfully!";
    } catch (Exception e) {
      return "Category can not deleted!";
    }

  }

  @Override
  public String insert(CreateCategoryRequest request) {
    var category = ObjectMapperUtils.map(request, Category.class);
    repository.save(category);
    return "Category added successfully!";
  }

  @Override
  public String update(Integer id, UpdateCategoryRequest request) {
    var categoryToUpdate = repository.findById(id).orElseThrow();
    categoryToUpdate.setName(request.getName());
    repository.save(categoryToUpdate);
    return "Category Updated Successfully!";
  }
  

  //private CategoryResponse categoryToCategoryResponse(Category category) {
  //  return CategoryResponse.builder()
  //                         .id(category.getId())
  //                         .name(category.getName())
  //                         .build();
  //}
}
