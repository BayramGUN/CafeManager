package com.inn.cafe.user.service;

import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inn.cafe.config.jwt.JwtService;
import com.inn.cafe.user.dao.UserRepository;
import com.inn.cafe.user.data.User;
import com.inn.cafe.user.data.enums.Role;
import com.inn.cafe.user.data.enums.Status;
import com.inn.cafe.user.dto.command.CreateUserRequest;
import com.inn.cafe.user.dto.command.UpdateUserRequest;
import com.inn.cafe.user.dto.query.UserResponse;
import com.inn.cafe.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;



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

  @Override
  public String createUserAsAdmin(CreateUserRequest request) {
    
    var isEmailExists = repository.existsByEmail(request.getEmail());
    if (isEmailExists)
      throw new NonUniqueObjectException("This email already using: {}", request.getEmail());

    var isContactNumberExists = repository.existsByContactNumber(request.getContactNumber());
    if (isContactNumberExists)
      throw new NonUniqueObjectException("This username already using: {}", request.getContactNumber());

    var isUsernameExists = repository.existsByUsername(request.getContactNumber());
    if (isUsernameExists)
      throw new NonUniqueObjectException("This contact number already using: {}", request.getContactNumber());

    var user = User.builder()
                   .firstName(request.getFirstName())
                   .lastName(request.getLastName())
                   .email(request.getEmail())
                   .username(request.getUsername())
                   .contactNumber(request.getContactNumber())
                   .password(passwordEncoder.encode(request.getPassword()))
                   .role(Role.ADMIN)
                   .status(Status.ACTIVE)
                   .build();

    repository.save(user);
    var jwt = jwtService.generateToken(user);

    return "Admin created successfully! Here is token: " + jwt;
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
