package com.inn.cafe.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inn.cafe.user.dao.UserRepository;
import com.inn.cafe.user.data.User;
import com.inn.cafe.user.data.enums.Status;
import com.inn.cafe.user.dto.UpdateUserRequest;
import com.inn.cafe.user.dto.UserResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

  @Override
  public String update(Integer id, UpdateUserRequest request) {
    User userToUpdate = repository.findById(id).orElseThrow();
    userToUpdate.setContactNumber(request.getContactNumber());
    userToUpdate.setUsername(request.getUsername());
    try {
      repository.save(userToUpdate);
      return "User is updated Successfully!";
    } catch (Exception exception) {
      return exception.getMessage();
    }
  }
  
  @Override
  public String softDelete(Integer id) {
    User userToDelete = repository.findById(id).orElseThrow();
    userToDelete.setStatus(Status.INACTIVE);
    try {
      repository.save(userToDelete);
      return "User is soft-deleted Successfully!";
    } catch (Exception exception) {
      return exception.getMessage();
    }
  }
  //private UserResponse userToUserResponse(User user) {
  //  var response = UserResponse.builder()
  //                     .contactNumber(user.getContactNumber())
  //                     .fullName(user.getFirstName() + " " + user.getLastName())
  //                     .email(user.getEmail())
  //                     .id(user.getId())
  //                     .build();
  //  response.setUsername(user.getUsername());
  //  return response;
  //}
}
