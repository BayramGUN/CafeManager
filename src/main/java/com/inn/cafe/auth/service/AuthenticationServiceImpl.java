package com.inn.cafe.auth.service;

import org.hibernate.NonUniqueObjectException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inn.cafe.auth.dto.command.AuthenticationRequest;
import com.inn.cafe.auth.dto.command.PasswordRequest;
import com.inn.cafe.auth.dto.command.RegisterRequest;
import com.inn.cafe.auth.dto.query.AuthenticationResponse;
import com.inn.cafe.config.jwt.JwtService;
import com.inn.cafe.user.dao.UserRepository;
import com.inn.cafe.user.data.User;
import com.inn.cafe.user.data.enums.Role;
import com.inn.cafe.user.data.enums.Status;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  
  @Override
  public AuthenticationResponse register(RegisterRequest request) {

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
                   .role(Role.USER)
                   .status(Status.ACTIVE)
                   .build();

    repository.save(user);
    var jwt = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
                                 .token(jwt)
                                 .build();
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    String username = 
      (request.getEmail() != null) 
        ? request.getEmail() : (request.getUsername() != null) 
        ? request.getUsername() : request.getContactNumber();

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        username,
        request.getPassword()
      )
    );

    var user = repository.findByEmail(request.getEmail())
                         .or(() -> repository.findByUsername(request.getUsername()))
                         .or(() -> repository.findByContactNumber(request.getContactNumber()))
                         .orElseThrow();

    var jwt = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
                                 .token(jwt)
                                 .build();
  }

  @Override
  public AuthenticationResponse forgotPassword(PasswordRequest request) {
    var isEmailExists = repository.existsByEmail(request.getEmail());

    if (!isEmailExists)
      throw new UsernameNotFoundException("This email is not used!");
      
    User userToUpdate = repository.findById(request.getId()).orElseThrow();
      
    userToUpdate.setPassword(passwordEncoder.encode(request.getPassword()));
    repository.save(userToUpdate);
    var jwt = jwtService.generateToken(userToUpdate);

    return AuthenticationResponse.builder()
                                 .token(jwt)
                                 .build();
  }

  
  
}
