package com.inn.cafe.category.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inn.cafe.category.dao.CategoryRepository;
import com.inn.cafe.category.data.Category;
import com.inn.cafe.category.dto.query.CategoryResponse;
import com.inn.cafe.product.dto.query.ProductResponse;
import com.inn.cafe.product.service.query.ProductQueryService;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

  private final CategoryRepository repository;
  private final ProductQueryService productQueryService;

  @Override
  public List<CategoryResponse> getAll() {
    return ObjectMapperUtils.mapAll(repository.findAll(), CategoryResponse.class);
  }

  @Override
  public CategoryResponse getById(Integer id) {
    var category = repository.findById(id).orElseThrow(
      () -> new RuntimeException("Category not Found!"));
    return ObjectMapperUtils.map(category, CategoryResponse.class);    
  }

  @Override
  public List<ProductResponse> getCategoryProducts(Integer id) {
    var products = productQueryService.getAllByCategoryId(id);
    return products;   
  }
    
  @Override
  public CategoryResponse getByName(String name) {
    Category category = repository.findByName("name").orElseThrow(      
      () -> new RuntimeException("Category not Found!"));
    return ObjectMapperUtils.map(category, CategoryResponse.class);
  }
}
