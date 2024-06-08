package com.inn.cafe.product.web;

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

import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;
import com.inn.cafe.product.dto.query.ProductResponse;

@RequestMapping("/api/v1/products")
public interface ProductController {

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll();

  @GetMapping("/getBy")
  public ResponseEntity<ProductResponse> getBy(
    @RequestParam(name = "name") final String name 
  );

  @PostMapping("/insert")
  public ResponseEntity<String> create(
    @RequestBody(required = true) CreateProductRequest request
  );

  @PutMapping("/{id}/update")
  public ResponseEntity<String> update(
    @PathVariable final Integer id,
    @RequestBody(required = true) UpdateProductRequest request
  );
  
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String> delete(
    @PathVariable final Integer id
  );

}
