package com.inn.cafe.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.auth.dto.command.AuthenticationRequest;
import com.inn.cafe.auth.dto.command.RegisterRequest;
import com.inn.cafe.auth.dto.query.AuthenticationResponse;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthenticationController {

  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody(required = true) RegisterRequest request
  );

  @PostMapping("/authenticate")
  @ResponseBody
  public ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody(required = true) AuthenticationRequest request
  );

} 
