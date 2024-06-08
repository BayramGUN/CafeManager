package com.inn.cafe.product.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inn.cafe.product.dao.ProductRepository;
import com.inn.cafe.product.data.Product;
import com.inn.cafe.product.dto.query.ProductCategoryFilterQuery;
import com.inn.cafe.product.dto.query.ProductResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

  private final ProductRepository repository;
  
  @Override
  public List<ProductResponse> getAll() {
    return ObjectMapperUtils.mapAll(repository.findAll(),ProductResponse.class);
  }

  @Override
  public ProductResponse getById(Integer id) {
    Product product = repository.findById(id).orElseThrow(
      () -> new RuntimeException("Product not found!")
    );
    return ObjectMapperUtils.map(product, ProductResponse.class);
  }

  @Override
  public ProductResponse getByName(String name) {
    Product product = repository.findByName(name).orElseThrow(
      () -> new RuntimeException("Product not found!")
    );
    return ObjectMapperUtils.map(product, ProductResponse.class);
  }

  @Override
  public List<ProductResponse> getAllByCategory(ProductCategoryFilterQuery filter) {
    List<Product> products;
    if (filter.getId() != null)
      products = repository.findAllByCategoryId(filter.getId());
    else 
      products = repository.findAllByCategoryName(filter.getName());
    
    return ObjectMapperUtils.mapAll(products, ProductResponse.class);
      
  }

  @Override
  public List<ProductResponse> getAllByCategoryId(Integer categoryId) {
    var products = repository.findAllByCategoryId(categoryId);
    return ObjectMapperUtils.mapAll(products, ProductResponse.class);
  }
  
}
