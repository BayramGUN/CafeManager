package com.inn.cafe.product.dao;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.product.data.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  Optional<Product> findByName(String name);
  List<Product> findAllByCategoryId(Integer categoryId);
  List<Product> findAllByCategoryName(String categoryName);
  
}
