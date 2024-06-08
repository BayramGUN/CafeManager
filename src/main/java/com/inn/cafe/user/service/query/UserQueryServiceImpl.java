package com.inn.cafe.user.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inn.cafe.user.dao.UserRepository;
import com.inn.cafe.user.data.User;
import com.inn.cafe.user.dto.query.UserResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

  private final UserRepository repository;

  @Override
  public List<UserResponse> getAll() {
    return ObjectMapperUtils.mapAll(repository.findAll(), UserResponse.class);
  }

  @Override
  public UserResponse getById(Integer id) {
    User user = repository.findById(id).orElseThrow();
    return ObjectMapperUtils.map(user, UserResponse.class);
  }

  @Override
  public UserResponse getByEmail(String email) {
    return ObjectMapperUtils.map(repository.findByEmail(email).orElseThrow(), UserResponse.class);    
  }
}
