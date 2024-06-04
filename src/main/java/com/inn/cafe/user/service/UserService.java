package com.inn.cafe.user.service;


import java.util.List;

import com.inn.cafe.user.dto.command.CreateUserRequest;
import com.inn.cafe.user.dto.command.UpdateUserRequest;
import com.inn.cafe.user.dto.query.UserResponse;


public interface UserService {
  // Queries: 
  public List<UserResponse> getAll();
  public UserResponse getById(Integer id);
  public UserResponse getByEmail(String email);

  // Commands:
  public String createUserAsAdmin(CreateUserRequest request);
  public String update(Integer id, UpdateUserRequest request);
  public String softDelete(Integer id);

}
