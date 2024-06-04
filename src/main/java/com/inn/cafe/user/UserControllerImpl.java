package com.inn.cafe.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.exception.CustomBadRequestException;
import com.inn.cafe.exception.CustomNotFoundException;
import com.inn.cafe.user.dto.command.CreateUserRequest;
import com.inn.cafe.user.dto.command.UpdateUserRequest;
import com.inn.cafe.user.dto.query.UserResponse;
import com.inn.cafe.user.exception.UserNotFoundException;
import com.inn.cafe.user.service.UserService;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;

  @Override
  @RolesAllowed(value = { "ADMIN" })
  @GetMapping
  @ResponseBody
  public ResponseEntity<List<UserResponse>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }
  
  @Override
  @GetMapping("/getBy")
  @ResponseBody
  public ResponseEntity<UserResponse> getByEmail(
    @RequestParam(name = "email") String email
  ) {
    try {
      return ResponseEntity.ok(userService.getByEmail(email));
    } catch (Exception exception) {
      throw new UserNotFoundException(email + " can not found!");
    }
  }

  @Override
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<UserResponse> getById(@PathVariable Integer id) {
    try {
      
      return ResponseEntity.ok(userService.getById(id));
    } catch (Exception exception) {
      // TODO: handle exception
      throw new UserNotFoundException(exception.getMessage());
    }
  }

  @Override
  @PutMapping("/{id}/update")
  public ResponseEntity<String> update(
    @PathVariable Integer id, 
    @RequestBody(required = true) UpdateUserRequest request) {
    return ResponseEntity.ok(userService.update(id, request));
  }

  @Override
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    return ResponseEntity.ok(userService.softDelete(id));
  }

  @Override
  @RolesAllowed(value = { "ADMIN" })
  @PostMapping("/insert/admin")
  @ResponseBody
  public ResponseEntity<String> insertAdmin(CreateUserRequest request) {
    try {
      return ResponseEntity.ok(userService.createUserAsAdmin(request));
    } catch (Exception exception) {
      throw new CustomBadRequestException(exception.getMessage());
    }
  }
  
}
