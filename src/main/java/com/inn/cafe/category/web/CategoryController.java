package com.inn.cafe.category.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.category.dto.command.CreateCategoryRequest;
import com.inn.cafe.category.dto.command.UpdateCategoryRequest;
import com.inn.cafe.category.dto.query.CategoryResponse;
import com.inn.cafe.product.dto.query.ProductResponse;

@RestController
@RequestMapping("/api/v1/categories")
public interface CategoryController {

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAll();

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable final Integer id);

  @GetMapping("/{id}/products")
  public ResponseEntity<List<ProductResponse>> getCategoryProducts(
    @PathVariable final Integer id
  );

  @GetMapping("/getBy")
  public ResponseEntity<CategoryResponse> getByName(
    @RequestParam(name = "name") final String name
  );

  @PostMapping("/insert")
  public ResponseEntity<CategoryResponse> add(
    @RequestBody(required = true) final CreateCategoryRequest request
  );

  @PutMapping("/{id}/update")
  public ResponseEntity<String> update(
    @PathVariable final Integer id,
    @RequestBody(required = true) final UpdateCategoryRequest request
  );
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String> delete(
    @PathVariable final Integer id
  );
  
}
