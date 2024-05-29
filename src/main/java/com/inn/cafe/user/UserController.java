package com.inn.cafe.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.user.dto.UpdateUserRequest;
import com.inn.cafe.user.dto.UserResponse;

@RestController
@RequestMapping("/api/v1/users")
public interface UserController {
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<UserResponse>> getAll();

  @GetMapping("/getBy")
  @ResponseBody
  public ResponseEntity<UserResponse> getByEmail(
    @RequestParam(name = "email") String email
  );

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<UserResponse> getById(@PathVariable Integer id);

  @PutMapping("/{id}/update")
  @ResponseBody
  public ResponseEntity<String> update(
    @PathVariable(required = true) Integer id,
    @RequestBody(required = true) UpdateUserRequest request
  );

  @DeleteMapping("/{id}/delete")
  @ResponseBody
  public ResponseEntity<String> delete(
    @PathVariable(required = true) Integer id 
  );


} 