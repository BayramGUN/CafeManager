package com.inn.cafe.category.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.category.dto.command.CreateCategoryRequest;
import com.inn.cafe.category.dto.command.UpdateCategoryRequest;
import com.inn.cafe.category.dto.query.CategoryResponse;
import com.inn.cafe.category.service.command.CategoryCommandService;
import com.inn.cafe.category.service.query.CategoryQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

  private final CategoryCommandService commandService;
  private final CategoryQueryService queryService;

  @Override
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<CategoryResponse>> getAll() {
    return ResponseEntity.ok(queryService.getAll());
  }
  
  @Override
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<CategoryResponse> getById(
    @PathVariable Integer id
  ) {
    return ResponseEntity.ok(queryService.getById(id));
  }
    
  @Override
  @GetMapping("/getBy")
  @ResponseBody
  public ResponseEntity<CategoryResponse> getByName(String name) {
    return ResponseEntity.ok(queryService.getByName(name));
  }

  @Override
  @PostMapping("/insert")
  public ResponseEntity<String> add(
    @RequestBody(required = true) CreateCategoryRequest request) {
    return ResponseEntity.ok(commandService.insert(request));
  }

  @Override
  @PutMapping("/{id}/update")
  public ResponseEntity<String> update(
    @PathVariable Integer id, 
    @RequestBody(required = true) UpdateCategoryRequest request
  ) {
      return ResponseEntity.ok(commandService.update(id, request));
  }

  @Override
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    return ResponseEntity.ok(commandService.delete(id));
  }
  
}
