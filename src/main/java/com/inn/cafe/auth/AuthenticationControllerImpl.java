package com.inn.cafe.auth;

import org.hibernate.NonUniqueObjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.auth.dto.command.AuthenticationRequest;
import com.inn.cafe.auth.dto.command.RegisterRequest;
import com.inn.cafe.auth.dto.query.AuthenticationResponse;
import com.inn.cafe.auth.service.AuthenticationService;
import com.inn.cafe.exception.CustomBadRequestException;
import com.inn.cafe.exception.CustomNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class AuthenticationControllerImpl implements AuthenticationController {

  private final AuthenticationService authenticationService;

  @Override
  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody(required = true) RegisterRequest request
  ) {
    try {
      
      return ResponseEntity.ok(authenticationService.register(request));
    } catch (NonUniqueObjectException exception) {
      exception.printStackTrace();
      throw new CustomBadRequestException(exception.getMessage());
    }
  }
  
  @Override
  @PostMapping("/authenticate")
  @ResponseBody
  public ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody(required = true) AuthenticationRequest request
  ) {
    try {
      return ResponseEntity.ok(authenticationService.authenticate(request));
    } catch (Exception exception) {
        throw new CustomBadRequestException(exception.getMessage());
    }
  }
  
}
