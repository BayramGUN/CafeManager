package com.inn.cafe.product.service.command;

import org.springframework.stereotype.Service;

import com.inn.cafe.category.data.Category;
import com.inn.cafe.category.service.query.CategoryQueryService;
import com.inn.cafe.product.dao.ProductRepository;
import com.inn.cafe.product.data.Product;
import com.inn.cafe.product.data.enums.ProductStatus;
import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;
import com.inn.cafe.product.dto.query.ProductResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

  private final ProductRepository repository;
  private final CategoryQueryService categoryService;
  
  @Override
  public String delete(Integer id) {
    try {
      Product productToDelete = repository.findById(id).orElseThrow(
        () -> new RuntimeException("Product not found!")
      );
      repository.delete(productToDelete);
      return "Product is deleted successfully!";  
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }

  @Override
  public ProductResponse insert(CreateProductRequest request) {
    Category category = ObjectMapperUtils.map(categoryService.getById(request.getCategoryId()), Category.class);

    Product entity = new Product();

    entity.setName(request.getName());
    entity.setDescription(request.getDescription());
    entity.setPrice(request.getPrice());
    entity.setStatus(ProductStatus.ACTIVE);

    entity.setCategory(category);

    Product savedProduct = repository.save(entity);

    return ObjectMapperUtils.map(savedProduct, ProductResponse.class);
  }

  @Override
  public String update(Integer id, UpdateProductRequest request) {
    Product productToUpdate = repository.findById(id).orElseThrow(
      () -> new RuntimeException("Product not found!")
    );
    ObjectMapperUtils.map(request, productToUpdate);
    repository.save(productToUpdate);
    return "Product is updated successfully!";
  }
  
}
