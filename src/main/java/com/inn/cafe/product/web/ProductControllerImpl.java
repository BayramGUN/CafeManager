package com.inn.cafe.product.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.product.dto.command.CreateProductRequest;
import com.inn.cafe.product.dto.command.UpdateProductRequest;
import com.inn.cafe.product.dto.query.ProductResponse;
import com.inn.cafe.product.service.command.ProductCommandService;
import com.inn.cafe.product.service.query.ProductQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

  private final ProductCommandService commandService;
  private final ProductQueryService queryService;

  @Override
  @PostMapping("/insert")
  public ResponseEntity<String> create(
    @RequestBody(required = true)
    CreateProductRequest request
  ) {
    return ResponseEntity.ok(commandService.insert(request));
  }
  
  @Override
  @PutMapping("/{id}/update")
  public ResponseEntity<String> update(
    @PathVariable
    final Integer id, 
    @RequestBody(required = true)
    final UpdateProductRequest request
  ) {
    return ResponseEntity.ok(commandService.update(id, request));
  }

  @Override
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String> delete(@PathVariable final Integer id) {
    return ResponseEntity.ok(commandService.delete(id));
  }

  @Override
  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAll() {
    return ResponseEntity.ok(queryService.getAll());
  }

  @Override
  @GetMapping("/getBy")
  public ResponseEntity<ProductResponse> getBy(String name) {
    return ResponseEntity.ok(queryService.getByName(name));
  }
  
}
