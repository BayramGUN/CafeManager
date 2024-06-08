package com.inn.cafe.product.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.product.data.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  Optional<Product> findByName(String name);
  
}
