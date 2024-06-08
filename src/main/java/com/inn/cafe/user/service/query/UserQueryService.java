package com.inn.cafe.user.service.query;

import java.util.List;

import com.inn.cafe.user.dto.query.UserResponse;

public interface UserQueryService {
  public List<UserResponse> getAll();
  public UserResponse getById(Integer id);
  public UserResponse getByEmail(String email);
  
}
