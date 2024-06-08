package com.inn.cafe.product.service.command;

import org.springframework.stereotype.Service;

import com.inn.cafe.product.dao.ProductRepository;
import com.inn.cafe.product.data.Product;
import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

  private final ProductRepository repository;
  
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
  public String insert(CreateProductRequest request) {
    Product entity = ObjectMapperUtils.map(request, Product.class);
    repository.save(entity);
    return "Product is added successfully!";
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
