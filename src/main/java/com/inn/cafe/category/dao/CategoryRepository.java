package com.inn.cafe.category.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.category.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<Category> findByName(String name);
}
